package com.example.myEcomProjectPractice.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.myEcomProjectPractice.Models.User;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

}
