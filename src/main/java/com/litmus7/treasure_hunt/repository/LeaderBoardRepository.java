package com.litmus7.treasure_hunt.repository;

import com.litmus7.treasure_hunt.model.LeaderBoard;
import com.litmus7.treasure_hunt.model.Participation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeaderBoardRepository extends JpaRepository<LeaderBoard, Participation> {

    public LeaderBoard findByParticipation(Participation participation);

    public Page<LeaderBoard> findByParticipationInOrderByLevelDescLastModifiedAsc(List<Participation> participations, Pageable pageable);



}
