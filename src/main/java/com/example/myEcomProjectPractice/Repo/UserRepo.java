package com.example.myEcomProjectPractice.Repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.myEcomProjectPractice.Models.User;

public interface UserRepo extends JpaRepository<User,Long>{
    Optional<User> findByEmail(String email);
}
