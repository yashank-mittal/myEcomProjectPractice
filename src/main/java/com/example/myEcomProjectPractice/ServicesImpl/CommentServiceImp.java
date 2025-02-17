package com.example.myEcomProjectPractice.ServicesImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

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

@Service
@RequiredArgsConstructor
public class CommentServiceImp implements CommentService {

    private final CommentRepo commentRepo;
    private final ProductRepo productRepo;
    private final UserRepo userRepo;
    private final CommentMapper commentMapper;
    
    @Override
    public CommentDTO addComment(Long productId, Long userId, CommentDTO commentDTO) {
        Product product = productRepo.findById(productId)
                            .orElseThrow(() -> new ResourceNotFoundException("Product Not Found"));
        
        User user = userRepo.findById(userId)
                    .orElseThrow(() -> new ResourceNotFoundException("User Not Found"));

        Comment comment = commentMapper.toCommentEntity(commentDTO);
        comment.setProduct(product);
        comment.setUser(user);
        Comment savedComment = commentRepo.save(comment);
        return commentMapper.tCommentDTO(savedComment);
    }

    @Override
    public List<CommentDTO> getAllCommentsByProduct(Long productId) {
        List<Comment> comments = commentRepo.findByProductProdId(productId); 
        return comments.stream()
        .map(commentMapper::tCommentDTO)
        .collect(Collectors.toList());
    }
    
    
}
