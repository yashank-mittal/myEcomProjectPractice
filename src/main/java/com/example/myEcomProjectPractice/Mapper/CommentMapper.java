package com.example.myEcomProjectPractice.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.myEcomProjectPractice.DTO.CommentDTO;
import com.example.myEcomProjectPractice.Models.Comment;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    
    @Mapping(source = "user.userId", target = "userId")
    CommentDTO tCommentDTO(Comment comment);

    @Mapping(source = "userId", target = "user.userId")
    @Mapping(target = "product.prodId",ignore = true)
    Comment toCommentEntity(CommentDTO commentDTO);
}
