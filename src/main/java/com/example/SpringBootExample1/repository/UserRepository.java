package com.example.SpringBootExample1.repository;

import com.example.SpringBootExample1.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

//    User findByUsername(String username);

    User findByEmail(String email);


}
