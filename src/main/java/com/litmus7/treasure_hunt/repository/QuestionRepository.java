package com.litmus7.treasure_hunt.repository;

import com.litmus7.treasure_hunt.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question,Integer> {

    public List<Question> findByActiveTrueAndContestIdOrderByQuestionIdAsc(int contestId);

    public Question findByQuestionId(int questionId);

}
