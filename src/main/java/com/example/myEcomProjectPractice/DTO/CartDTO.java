package com.example.myEcomProjectPractice.DTO;

import lombok.Data;

import java.util.List;

@Data
public class CartDTO {
    private Long id;
    private Long userId; //usedId
    private List<CartItemDTO> items;
}