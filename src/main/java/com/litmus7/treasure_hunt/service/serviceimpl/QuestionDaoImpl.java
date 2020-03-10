package com.litmus7.treasure_hunt.service.serviceimpl;

import com.litmus7.treasure_hunt.dto.requestdto.AddCluesToQuestionRequest;
import com.litmus7.treasure_hunt.dto.requestdto.CreateClue;
import com.litmus7.treasure_hunt.dto.requestdto.CreateQuestionDto;
import com.litmus7.treasure_hunt.dto.responsedto.ClueResponse;
import com.litmus7.treasure_hunt.dto.responsedto.QuestionResponse;
import com.litmus7.treasure_hunt.exception.AppException;
import com.litmus7.treasure_hunt.model.Clue;
import com.litmus7.treasure_hunt.model.Question;
import com.litmus7.treasure_hunt.repository.ClueRepository;
import com.litmus7.treasure_hunt.repository.QuestionRepository;
import com.litmus7.treasure_hunt.service.QuestionDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionDaoImpl implements QuestionDao {

    private static final Logger logger = LogManager.getLogger(QuestionDaoImpl.class);

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    ClueRepository clueRepository;

    @Transactional
    @Override
    public boolean createNewQuestion(CreateQuestionDto createQuestionDto) throws AppException {

        Question question = new Question();


        question.setContestId(createQuestionDto.getContestId());
        question.setQuestion(createQuestionDto.getQuestion());
        question.setAudioURL(createQuestionDto.getAudioURL());
        question.setImageURL(createQuestionDto.getImageURL());
        question.setAnswer(createQuestionDto.getAnswer());
        question.setCurrent_clue(createQuestionDto.getCurrentClue());
        question.setActive(createQuestionDto.isActive());

        List<Clue> clues = new ArrayList<>();

        for (CreateClue clueDto : createQuestionDto.getClues()) {
            Clue clue = new Clue();
            clue.setClueDescription(clueDto.getClueDescription());
            clue.setImageURL(clueDto.getImageURL());
            clue.setAudioURL(clueDto.getAudioURL());
            clue.setActive(clueDto.isActive());
            clue.setQuestion(question);
            question.getClues().add(clue);
        }
        try {
            questionRepository.save(question);
            logger.info("Question saved successfully.");
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
        return true;
    }

    @Transactional
    @Override
    public List<QuestionResponse> fetchAllQuestions(int contestId) throws AppException {

        List<Question> quetions= questionRepository.findByActiveTrueAndContestIdOrderByQuestionIdAsc(contestId);

        List<QuestionResponse> questionResponses=new ArrayList<>();

        for( Question question:quetions){

            QuestionResponse questionResponse=new QuestionResponse();

            questionResponse.setQuestionId(question.getQuestionId());
            questionResponse.setQuestion(question.getQuestion());
            questionResponse.setImageURL(question.getImageURL());
            questionResponse.setAudioURL(question.getAudioURL());
            questionResponse.setAnswer(question.getAnswer());

            List<ClueResponse> responseclues=new ArrayList<>();

            for (Clue clue:question.getClues()) {
                ClueResponse clueResponse = new ClueResponse();
                clueResponse.setClueDescription(clue.getClueDescription());
                clueResponse.setClueId(clue.getClueId());
                clueResponse.setImageURL(clue.getImageURL());
                clueResponse.setAudioURL(clue.getAudioURL());
                responseclues.add(clueResponse);
            }

            questionResponse.setClues(responseclues);

            questionResponses.add(questionResponse);
        }

        return questionResponses;
    }

    @Transactional
    @Override
    public boolean addCluesToExistingQuestion(AddCluesToQuestionRequest addCluesToQuestionRequest) {

        Question question= Optional.ofNullable(questionRepository.findByQuestionId(addCluesToQuestionRequest.getQuestionId()))
                .orElseThrow(()-> new AppException("Invalid question details", HttpStatus.NOT_FOUND));


        addCluesToQuestionRequest.getClues().forEach(clue->{
            Clue newClue=new Clue();
            newClue.setQuestion(question);
            newClue.setClueDescription(clue.getClueDescription());
            newClue.setActive(clue.isActive());
            newClue.setImageURL(clue.getImageURL());
            newClue.setAudioURL(clue.getAudioURL());
            clueRepository.save(newClue);
        });


        return true;
    }

    @Transactional
    @Override
    public boolean changeQuestionStatus(int questionId) {

        Question question=Optional.ofNullable(questionRepository.findByQuestionId(questionId))
                .orElseThrow(()-> new AppException("Question is invalid",HttpStatus.NOT_FOUND));

        question.setActive(!question.isActive());

        questionRepository.save(question);


        return question.isActive();
    }
}
