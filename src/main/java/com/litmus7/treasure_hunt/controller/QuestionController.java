package com.litmus7.treasure_hunt.controller;

import com.litmus7.treasure_hunt.dto.requestdto.AddCluesToQuestionRequest;
import com.litmus7.treasure_hunt.dto.requestdto.CreateQuestionDto;
import com.litmus7.treasure_hunt.dto.responsedto.QuestionResponse;
import com.litmus7.treasure_hunt.dto.responsedto.ResponseInfo;
import com.litmus7.treasure_hunt.model.Question;
import com.litmus7.treasure_hunt.service.QuestionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class QuestionController {

    @Autowired
    QuestionDao questionDaoImpl;

    @PostMapping("/question")
    public ResponseEntity<ResponseInfo> createQuestion(@RequestBody CreateQuestionDto questionDto) {
        ResponseInfo responseInfo = new ResponseInfo(201, "CREATED");

        boolean isCreated = questionDaoImpl.createNewQuestion(questionDto);
        responseInfo.setPayload(isCreated);

        return new ResponseEntity<>(responseInfo, HttpStatus.CREATED);

    }

    @GetMapping("/question/{contestId}")
    public  ResponseEntity<ResponseInfo> getActiveQuestionsForContest(@PathVariable("contestId") int contestId ){
        ResponseInfo responseInfo=new ResponseInfo(200,"OK");

        List<QuestionResponse> questionResponses=questionDaoImpl.fetchAllQuestions(contestId);

        responseInfo.setPayload(questionResponses);

        return new ResponseEntity<>(responseInfo,HttpStatus.OK);
    }

    @PostMapping("/question/clues")
    public ResponseEntity<ResponseInfo> addCluesToQuestion(@Valid @RequestBody AddCluesToQuestionRequest cluesToQuestionRequest){

        ResponseInfo responseInfo=new ResponseInfo(200,"OK");

        responseInfo.setPayload(questionDaoImpl.addCluesToExistingQuestion(cluesToQuestionRequest));

        return new ResponseEntity<>(responseInfo,HttpStatus.OK);
    }

    @PutMapping("/question/status")
    public ResponseEntity<ResponseInfo> changeQuestionStatus(@RequestParam int questionId){
        ResponseInfo responseInfo=new ResponseInfo(200,"Ok");

        responseInfo.setPayload(questionDaoImpl.changeQuestionStatus(questionId));

        return new ResponseEntity<>(responseInfo,HttpStatus.OK);
    }

}
