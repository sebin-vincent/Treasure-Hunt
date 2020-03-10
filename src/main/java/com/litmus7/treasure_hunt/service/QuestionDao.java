package com.litmus7.treasure_hunt.service;

import com.litmus7.treasure_hunt.dto.requestdto.AddCluesToQuestionRequest;
import com.litmus7.treasure_hunt.dto.requestdto.CreateQuestionDto;
import com.litmus7.treasure_hunt.dto.responsedto.QuestionResponse;
import com.litmus7.treasure_hunt.exception.AppException;

import java.util.List;

public interface QuestionDao {

    public boolean createNewQuestion(CreateQuestionDto createQuestionDto) throws AppException;

    public List<QuestionResponse> fetchAllQuestions(int contestId) throws AppException;

    public boolean addCluesToExistingQuestion(AddCluesToQuestionRequest addCluesToQuestionRequest);

    public boolean changeQuestionStatus(int questionId);


}
