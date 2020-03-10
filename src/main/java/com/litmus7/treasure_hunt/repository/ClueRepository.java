package com.litmus7.treasure_hunt.repository;

import com.litmus7.treasure_hunt.model.Clue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClueRepository extends JpaRepository<Clue,Integer> {
}
