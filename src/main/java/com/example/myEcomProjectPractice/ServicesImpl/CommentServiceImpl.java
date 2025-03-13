package com.example.myEcomProjectPractice.ServicesImpl;

import com.example.myEcomProjectPractice.DTO.CommentDTO;
import com.example.myEcomProjectPractice.Mapper.CommentMapper;
import com.example.myEcomProjectPractice.Models.Comment;
import com.example.myEcomProjectPractice.Models.Product;
import com.example.myEcomProjectPractice.Models.User;
import com.example.myEcomProjectPractice.Repo.CommentRepo;
import com.example.myEcomProjectPractice.Repo.ProductRepo;
import com.example.myEcomProjectPractice.Repo.UserRepo;
import com.example.myEcomProjectPractice.Services.CommentService;
import com.example.myEcomProjectPractice.exception.ResourceNotFoundException;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepo commentRepository;
    private final ProductRepo productRepository;
    private final UserRepo userRepository;
    private final CommentMapper commentMapper;

    public CommentDTO addComment(Long productId, Long userId, CommentDTO commentDTO){
        Product product = productRepository.findById(productId)
                .orElseThrow(()-> new ResourceNotFoundException("Product not found"));
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("User not found"));

        Comment comment = commentMapper.toEntity(commentDTO);
        comment.setProduct(product);
        comment.setUser(user);
        Comment savedComment = commentRepository.save(comment);
        return commentMapper.toDTO(savedComment);

    }

    public List<CommentDTO> getCommentsByProduct(Long productId){
        List<Comment> comments = commentRepository.findByProductId(productId);
        return comments.stream()
                .map(commentMapper::toDTO)
                .collect(Collectors.toList());
    }

}