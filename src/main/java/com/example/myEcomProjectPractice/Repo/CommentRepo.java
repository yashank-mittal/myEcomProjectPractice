package com.example.myEcomProjectPractice.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.myEcomProjectPractice.Models.Comment;

import java.util.List;

public interface CommentRepo extends JpaRepository<Comment, Long> {
    List<Comment> findByProductId(Long productId);
}