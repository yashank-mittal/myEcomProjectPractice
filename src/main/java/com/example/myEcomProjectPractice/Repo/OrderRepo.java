package com.example.myEcomProjectPractice.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.myEcomProjectPractice.Models.Orders;

public interface OrderRepo extends JpaRepository<Orders,Long> {
    List<Orders> findByUserUserId(long userId);
}
