package com.litmus7.treasure_hunt.service.serviceimpl;

import com.litmus7.treasure_hunt.exception.AppException;
import com.litmus7.treasure_hunt.model.Contest;
import com.litmus7.treasure_hunt.repository.ContestRepository;
import com.litmus7.treasure_hunt.service.ContestDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ContestDaoImpl implements ContestDao {

    private static final Logger logger = LogManager.getLogger(ContestDaoImpl.class);

    @Autowired
    ContestRepository contestRepository;


    @Transactional
    @Override
    public Contest createNewContest(String contestName, boolean isEnabled) throws AppException {

        Contest contest=new Contest();

        contest.setContestName(contestName);
        contest.setEnabled(isEnabled);

        try{
            contest=contestRepository.save(contest);
            logger.info("contest created {}",contestName);
        }catch (Exception e){
            logger.error(e.getMessage());
            throw new AppException(e.getMessage(), HttpStatus.CONFLICT);
        }

        return contest;
    }

    @Override
    public List<Contest> fetchActiveContest() throws AppException {

        List<Contest> contests=null;

        contests=contestRepository.findByIsEnabledTrue();

        return contests;
    }

    @Transactional
    @Override
    public boolean changeContestStatus(int contestId) throws AppException {

        Contest contest=Optional.ofNullable(contestRepository.findByContestId(contestId))
                .orElseThrow(()-> new AppException("Contest does not exist",HttpStatus.NOT_FOUND));

        contest.setEnabled(!contest.isEnabled());

        contestRepository.save(contest);

        return contest.isEnabled();
    }

}
