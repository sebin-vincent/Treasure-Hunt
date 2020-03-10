package com.litmus7.treasure_hunt.service;

import com.litmus7.treasure_hunt.dto.requestdto.FetchCurrentQuestionDto;
import com.litmus7.treasure_hunt.dto.requestdto.SubmitAnswerRequest;
import com.litmus7.treasure_hunt.dto.responsedto.CurrentQuestionResponse;
import com.litmus7.treasure_hunt.dto.responsedto.LeaderBoardResponse;

import com.litmus7.treasure_hunt.dto.responsedto.SubmitAnswerResponse;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface UserDao {

    public CurrentQuestionResponse getCurrentQuestion(FetchCurrentQuestionDto currentQuestionDto);

    public List<LeaderBoardResponse> getLeaderboardOfContest(int contestId, Pageable pageable);

    public SubmitAnswerResponse submitQuestionAnswer(SubmitAnswerRequest submitAnswerRequest);

    public boolean changeUserStatus(int userId);

}
