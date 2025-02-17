package com.example.myEcomProjectPractice.DTO;

import java.util.List;

import lombok.Data;

@Data
public class CartDTO {
    private Long cartId;
    private Long userId;
    private List<CartItemDTO> Items;
}
