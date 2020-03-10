package com.litmus7.treasure_hunt.repository;

import com.litmus7.treasure_hunt.model.Contest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContestRepository extends JpaRepository<Contest,Integer> {

    public List<Contest> findByIsEnabledTrue();

    public Contest findByContestId(int contestId);


}
