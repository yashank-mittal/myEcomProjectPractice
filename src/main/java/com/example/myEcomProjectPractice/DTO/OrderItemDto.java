package com.example.myEcomProjectPractice.DTO;

import java.math.BigDecimal;

import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class OrderItemDto {
    private long id;
    private Long productId;
    @Positive(message = "Order Itemm quantity must be positive")
    private Integer quantity;
    @Positive(message = "Order Item price must be positive")
    private BigDecimal price;
}
