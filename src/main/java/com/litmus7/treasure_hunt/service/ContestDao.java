package com.litmus7.treasure_hunt.service;

import com.litmus7.treasure_hunt.exception.AppException;
import com.litmus7.treasure_hunt.model.Contest;

import java.util.List;

public interface ContestDao {
    public Contest createNewContest(String contestName,boolean isEnabled) throws AppException;

    public List<Contest> fetchActiveContest() throws AppException;

    public boolean changeContestStatus(int contestId) throws AppException;
}
