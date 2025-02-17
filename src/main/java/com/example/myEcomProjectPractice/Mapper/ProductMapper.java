package com.example.myEcomProjectPractice.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.myEcomProjectPractice.DTO.CommentDTO;
import com.example.myEcomProjectPractice.DTO.ProductDTO;
import com.example.myEcomProjectPractice.Models.Comment;
import com.example.myEcomProjectPractice.Models.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(source = "imageUrl",target = "imageUrl")
    ProductDTO toProductDTO(Product product);

    @Mapping(source = "imageUrl", target = "imageUrl")
    Product toProductEntity(ProductDTO productDTO);

    @Mapping(source = "user.userId", target = "userId")
    CommentDTO toCommentDTO(Comment comment);

    @Mapping(source = "userId", target = "user.userId")
    @Mapping(target = "product.prodId",ignore = true)
    Comment toCommentEntity(CommentDTO commentDTO);

}
