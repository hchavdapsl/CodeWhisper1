package com.codewhisper.demo.repository;

//repository for user with findById, findByUsername and findByName

import com.codewhisper.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findById(long id);
//    User findByUserName(String userName);
    User findByName(String name);
//    Optional<User> findByUsername(String username, String password);
    User findByEmail(String email);
    User findByUserNameAndPassword(String userName, String password);

    User findByUserName(String userName);
}