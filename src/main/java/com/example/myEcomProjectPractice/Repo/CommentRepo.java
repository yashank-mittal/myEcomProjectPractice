package com.example.myEcomProjectPractice.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.example.myEcomProjectPractice.Models.Comment;

public interface CommentRepo extends JpaRepository<Comment,Long> {
    List<Comment> findByProductProdId(long prodId);
}
