package com.example.myEcomProjectPractice.DTO;

import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class CartItemDTO {
    private Long id;
    private long productId;
    @Positive(message = "Cart Item quantity must be positive")
    private Integer quantity;
}
