package com.example.myEcomProjectPractice.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.myEcomProjectPractice.DTO.CartDTO;
import com.example.myEcomProjectPractice.DTO.CartItemDTO;
import com.example.myEcomProjectPractice.Models.Cart;
import com.example.myEcomProjectPractice.Models.CartItem;

@Mapper(componentModel = "spring")
public interface CartMapper {

    @Mapping(source = "user.userId", target = "userId")
    CartDTO toCartDTO(Cart cart);

    @Mapping(source = "userId", target = "user.userId")
    Cart toCartEntity(CartDTO cartDTO);

   // CartMapper INSTANCE = Mappers.getMapper(CartMapper.class);

   // CartItem Mapping
   @Mapping(source = "product.prodId", target = "productId")
   CartItemDTO toCartItemDTO(CartItem cartItem);

   @Mapping(source = "productId", target = "product.prodId")
   CartItem toCartItemEntity(CartItemDTO cartItemDTO);
}
