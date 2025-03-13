package com.example.myEcomProjectPractice.Mapper;

import com.example.myEcomProjectPractice.DTO.CommentDTO;
import com.example.myEcomProjectPractice.DTO.ProductDTO;
import com.example.myEcomProjectPractice.Models.Comment;
import com.example.myEcomProjectPractice.Models.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(target = "image", source = "image") //add mapping
    ProductDTO toDTO(Product product);

    @Mapping(target = "image", source = "image") //add mapping
    Product toEntity(ProductDTO productDTO);

    @Mapping(target = "userId",source = "user.id")
    CommentDTO toDTO(Comment comment);
    @Mapping(target = "user.id", source = "userId")
    @Mapping(target = "product", ignore = true)
    Comment toEntity(CommentDTO commentDTO);
}
