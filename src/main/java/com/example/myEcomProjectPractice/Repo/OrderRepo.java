package com.example.myEcomProjectPractice.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.myEcomProjectPractice.Models.Order;

import java.util.List;

public interface OrderRepo extends JpaRepository<Order, Long> {
    List<Order> findByUserId(Long userId);
}
