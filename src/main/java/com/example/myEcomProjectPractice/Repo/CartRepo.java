package com.example.myEcomProjectPractice.Repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.myEcomProjectPractice.Models.Cart;

public interface CartRepo extends JpaRepository<Cart,Long> {
    //Optinal means there can be user exists or not
    Optional<Cart> findByUserUserId(long userId);
}
