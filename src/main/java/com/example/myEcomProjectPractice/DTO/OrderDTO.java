package com.example.myEcomProjectPractice.DTO;

import java.time.LocalDateTime;
import java.util.List;

import com.example.myEcomProjectPractice.Models.Orders;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class OrderDTO {
    private Long orderId;
    private Long userId;
    @NotBlank(message = "Adress is required")
    private String address;
    @NotBlank(message = "phoneNumber is required")
    private String phoneNumber;
    private Orders.OrderStatus status;
    private LocalDateTime createdAt;
    private List<OrderItemDto> orderItem;
}
