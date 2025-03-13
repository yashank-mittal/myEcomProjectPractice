package com.example.myEcomProjectPractice.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.myEcomProjectPractice.DTO.CommentDTO;
import com.example.myEcomProjectPractice.Models.Comment;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    @Mapping(target = "userId",source = "user.id")
    CommentDTO toDTO(Comment comment);
    @Mapping(target = "user.id", source = "userId")
    @Mapping(target = "product", ignore = true)
    Comment toEntity(CommentDTO commentDTO);
}
