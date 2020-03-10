package com.litmus7.treasure_hunt.repository;

import com.litmus7.treasure_hunt.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    public User findByUserId(int userId);


}
