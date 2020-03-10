package com.litmus7.treasure_hunt.service.serviceimpl;

import com.litmus7.treasure_hunt.dto.requestdto.FetchCurrentQuestionDto;
import com.litmus7.treasure_hunt.dto.requestdto.SubmitAnswerRequest;
import com.litmus7.treasure_hunt.dto.responsedto.ClueResponse;
import com.litmus7.treasure_hunt.dto.responsedto.CurrentQuestionResponse;
import com.litmus7.treasure_hunt.dto.responsedto.LeaderBoardResponse;
import com.litmus7.treasure_hunt.dto.responsedto.SubmitAnswerResponse;
import com.litmus7.treasure_hunt.exception.AppException;
import com.litmus7.treasure_hunt.model.*;
import com.litmus7.treasure_hunt.repository.*;
import com.litmus7.treasure_hunt.service.UserDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.data.domain.Pageable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserDaoImpl implements UserDao {

    private static final Logger logger= LogManager.getLogger(UserDaoImpl.class);

    @Autowired
    private ContestRepository contestRepository;

    @Autowired
    private LeaderBoardRepository leaderBoardRepository;

    @Autowired
    private ParticipationRepository participationRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private UserRepository userRepository;



    @Transactional
    @Override
    public CurrentQuestionResponse getCurrentQuestion(FetchCurrentQuestionDto currentQuestionDto) {

        Participation currentParticipation;
        LeaderBoard leaderBoard;

        CurrentQuestionResponse currentQuestionResponse=new CurrentQuestionResponse();

        User user= Optional.ofNullable(userRepository.findByUserId(currentQuestionDto.getUserId()))
                .orElseThrow(()->new AppException("Invalid user", HttpStatus.NOT_FOUND));

        Contest contest=Optional.ofNullable(contestRepository.findByContestId(currentQuestionDto.getContestId()))
                .orElseThrow(()->new AppException("Invalid Contest details",HttpStatus.NOT_FOUND));

        Optional<Participation> participation=Optional.ofNullable(participationRepository.findByUserAndContest(user,contest));

        if(participation.isEmpty()){
            leaderBoard=createNewContestUser(user,contest);
        }else {
            currentParticipation=participation.get();
            leaderBoard=leaderBoardRepository.findByParticipation(currentParticipation);
        }

        Question question=leaderBoard.getQuestion();

        int currentClue=question.getCurrent_clue();

        int count=0;

        List<ClueResponse> clueResponses=new ArrayList<>();

        List<Clue> clues=question.getClues();

        for(int i=count;i<currentClue;i++){
            Clue clue=clues.get(i);
            ClueResponse clueResponse=new ClueResponse();
            clueResponse.setClueId(clue.getClueId());
            clueResponse.setClueDescription(clue.getClueDescription());
            clueResponse.setAudioURL(clue.getAudioURL());
            clueResponse.setImageURL(clue.getImageURL());
            clueResponses.add(clueResponse);
        }

        currentQuestionResponse.setQustionId(question.getQuestionId());
        currentQuestionResponse.setQuestion(question.getQuestion());
        currentQuestionResponse.setImageURL(question.getImageURL());
        currentQuestionResponse.setAudioURL(question.getAudioURL());
        currentQuestionResponse.setClueResponses(clueResponses);

        return currentQuestionResponse;
    }

    @Override
    public List<LeaderBoardResponse> getLeaderboardOfContest(int contestId, Pageable pageable) {

        List<LeaderBoardResponse> leaderBoardResponses=new ArrayList<>();

        Page<LeaderBoard> leaderBoardList = null;

        Contest contest=Optional.ofNullable(contestRepository.findByContestId(contestId))
                .orElseThrow(()->new AppException("Invalid Contest details",HttpStatus.NOT_FOUND));

        Optional<List<Participation>> participations=Optional.ofNullable(participationRepository.findByContest(contest));

        if(participations.isPresent()){
            leaderBoardList=  leaderBoardRepository.findByParticipationInOrderByLevelDescLastModifiedAsc(participations.get(),pageable);
        }

        leaderBoardList.getContent().forEach(leaderBoard -> {
            LeaderBoardResponse leaderBoardResponse=new LeaderBoardResponse();
            leaderBoardResponse.setName(leaderBoard.getParticipation().getUser().getName());
            leaderBoardResponse.setLevel(leaderBoard.getLevel());
            leaderBoardResponse.setTimeCompletd(leaderBoard.getLastModified());
            leaderBoardResponses.add(leaderBoardResponse);
        });

        return leaderBoardResponses;
    }

    @Transactional
    @Override
    public SubmitAnswerResponse submitQuestionAnswer(SubmitAnswerRequest submitAnswerRequest) {



        User user=Optional.ofNullable(userRepository.findByUserId(submitAnswerRequest.getUserId()))
                .orElseThrow(()->new AppException("User not found !",HttpStatus.NOT_FOUND));

        Contest contest=Optional.ofNullable((contestRepository.findByContestId(submitAnswerRequest.getContestId())))
                .orElseThrow(()-> new AppException("Contest details not found",HttpStatus.NOT_FOUND));

        Participation participation=participationRepository.findByUserAndContest(user,contest);

        LeaderBoard leaderBoard=leaderBoardRepository.findByParticipation(participation);

        String correctAnswer=leaderBoard.getQuestion().getAnswer();

        int level=leaderBoard.getLevel();


        if(correctAnswer.equals(submitAnswerRequest.getAnswer())){
            List<Question> questions=questionRepository.findByActiveTrueAndContestIdOrderByQuestionIdAsc(submitAnswerRequest.getContestId());
            int count=questions.size();
            if(count==level){
                return new SubmitAnswerResponse(true,"Last question");
            }else {
                level=level+1;
                leaderBoard.setQuestion(questions.get(level-1));
                leaderBoard.setLevel(level);
                leaderBoardRepository.save(leaderBoard);
                return new SubmitAnswerResponse(true,"Correct Answer");
            }
        }else{

            return new SubmitAnswerResponse(false,"Wrong answer");
        }

    }

    @Transactional
    @Override
    public boolean changeUserStatus(int userId) {

        User user=Optional.ofNullable(userRepository.findByUserId(userId))
                .orElseThrow(()-> new AppException("user not found",HttpStatus.NOT_FOUND));

        user.setEnabled(!user.isEnabled());

        userRepository.save(user);

        return user.isEnabled();
    }


    private LeaderBoard createNewContestUser(User user,Contest contest){

        List<Question> questions=questionRepository.findByActiveTrueAndContestIdOrderByQuestionIdAsc(contest.getContestId());

        Participation currentParticipation=new Participation(user,contest);
        currentParticipation.setActive(true);
        participationRepository.save(currentParticipation);

        LeaderBoard leaderBoard=new LeaderBoard();
        leaderBoard.setParticipation(currentParticipation);
        leaderBoard.setLevel(1);
        leaderBoard.setQuestion(questions.get(0));

        leaderBoard=leaderBoardRepository.save(leaderBoard);

        return leaderBoard;
    }


}
