package com.litmus7.treasure_hunt.repository;

import com.litmus7.treasure_hunt.model.Contest;
import com.litmus7.treasure_hunt.model.Participation;
import com.litmus7.treasure_hunt.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.codec.multipart.Part;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParticipationRepository extends JpaRepository<Participation,Integer> {

    public Participation findByUserAndContest(User user, Contest contest);

    public List<Participation> findByContest(Contest contest);



}
