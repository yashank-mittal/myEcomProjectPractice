package com.example.myEcomProjectPractice.Mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.myEcomProjectPractice.DTO.CartDTO;
import com.example.myEcomProjectPractice.DTO.CartItemDTO;
import com.example.myEcomProjectPractice.Models.Cart;
import com.example.myEcomProjectPractice.Models.CartItem;

@Mapper(componentModel = "spring")
public interface CartMapper {
    @Mapping(target = "userId", source = "user.id")
    CartDTO toDTO(Cart Cart);
    @Mapping(target="user.id", source = "userId")
    Cart toEntity(CartDTO cartDTO);

    @Mapping(target="productId", source="product.id")
    CartItemDTO toDTO(CartItem cartItem);

    @Mapping(target="product.id", source="productId")
    CartItem toEntity(CartItemDTO cartItemDTO);
}
