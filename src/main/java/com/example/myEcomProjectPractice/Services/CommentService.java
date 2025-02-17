package com.example.myEcomProjectPractice.Services;

import java.util.List;

import com.example.myEcomProjectPractice.DTO.CommentDTO;

public interface CommentService {
    public CommentDTO addComment(Long productId,Long userId,CommentDTO commentDTO);
    public List<CommentDTO> getAllCommentsByProduct(Long productId);
    
}
