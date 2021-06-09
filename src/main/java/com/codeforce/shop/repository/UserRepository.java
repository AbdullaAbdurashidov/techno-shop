package com.codeforce.shop.repository;

import com.codeforce.shop.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAll();

    User findByUserNameAndPassword(String email, String password);

    User findByUserName(String email);

    User findById(String hashID);

}
