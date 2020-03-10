package com.litmus7.treasure_hunt.controller;

import com.litmus7.treasure_hunt.dto.requestdto.ChangeContestStatus;
import com.litmus7.treasure_hunt.dto.requestdto.CreateContestDto;
import com.litmus7.treasure_hunt.dto.responsedto.ResponseInfo;
import com.litmus7.treasure_hunt.model.Contest;
import com.litmus7.treasure_hunt.service.ContestDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ContestController {

    @Autowired
    ContestDao contestDao;



    @PostMapping("/contest")
    public ResponseEntity<ResponseInfo> createContest(@Valid @RequestBody CreateContestDto contestDto){
        ResponseInfo responseInfo=new ResponseInfo(201,"Created");
        Contest contest = contestDao.createNewContest(contestDto.getContestName(),contestDto.isActive());
        responseInfo.setPayload(contest);
        return new ResponseEntity<>(responseInfo, HttpStatus.CREATED);
    }

    @GetMapping("/contests")
    public ResponseEntity<ResponseInfo> getActiveContests(){
        ResponseInfo responseInfo=new ResponseInfo(200,"OK");
        List<Contest> contests=contestDao.fetchActiveContest();
        responseInfo.setPayload(contests);
        return new ResponseEntity<>(responseInfo,HttpStatus.OK);
    }

    @PostMapping("/contest/status")
    public ResponseEntity<ResponseInfo> changeContestStatus(@RequestParam int contestId){

        ResponseInfo responseInfo=new ResponseInfo(200,"OK");

        responseInfo.setPayload(contestDao.changeContestStatus(contestId));

        return new ResponseEntity<>(responseInfo,HttpStatus.OK);
    }


}
