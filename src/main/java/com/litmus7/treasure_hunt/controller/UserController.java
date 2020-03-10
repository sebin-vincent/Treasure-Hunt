package com.litmus7.treasure_hunt.controller;

import com.litmus7.treasure_hunt.dto.requestdto.FetchCurrentQuestionDto;
import com.litmus7.treasure_hunt.dto.requestdto.SubmitAnswerRequest;
import com.litmus7.treasure_hunt.dto.responsedto.ResponseInfo;
import com.litmus7.treasure_hunt.service.UserDao;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class UserController {

    @Autowired
    private UserDao userDaoImpl;

    @GetMapping("/user/question")
    public ResponseEntity<ResponseInfo> getCurrentQuestion(@Valid @RequestBody FetchCurrentQuestionDto currentQuestionDto) {

        ResponseInfo responseInfo = new ResponseInfo(200, "OK");

        responseInfo.setPayload(userDaoImpl.getCurrentQuestion(currentQuestionDto));

        return new ResponseEntity<>(responseInfo, HttpStatus.OK);
    }

    @GetMapping("/leaderboard")
    public ResponseEntity<ResponseInfo> getLeaderBoardforContest(@RequestParam(defaultValue = "0") int contestId,
                                                                 @RequestParam(defaultValue = "0") int pageNo,
                                                                 @RequestParam(defaultValue = "10") int pageSize
    ) {

        if(pageSize<1){
            pageSize=10;
        }

        Pageable pageable=PageRequest.of(pageNo,pageSize);

        ResponseInfo responseInfo = new ResponseInfo(200, "OK");

        responseInfo.setPayload(userDaoImpl.getLeaderboardOfContest(contestId, pageable));

        return new ResponseEntity<>(responseInfo, HttpStatus.OK);

    }

    @PostMapping("/question/answer")
    public ResponseEntity<ResponseInfo> submitQuestionAnswer(@Valid @RequestBody SubmitAnswerRequest submitAnswerRequest){
        ResponseInfo responseInfo=new ResponseInfo(200,"OK");

        responseInfo.setPayload(userDaoImpl.submitQuestionAnswer(submitAnswerRequest));


        return new ResponseEntity<>(responseInfo,HttpStatus.OK);
    }

    @PostMapping("/user/status")
    public ResponseEntity<ResponseInfo> changeUserStatus(int userId){
        ResponseInfo responseInfo=new ResponseInfo(200,"Ok");

        responseInfo.setPayload(userDaoImpl.changeUserStatus(userId));

        return new ResponseEntity<>(responseInfo,HttpStatus.OK);

    }

}
