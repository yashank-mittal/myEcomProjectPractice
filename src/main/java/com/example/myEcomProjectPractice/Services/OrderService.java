package com.example.myEcomProjectPractice.Services;

import java.util.List;

import com.example.myEcomProjectPractice.DTO.OrderDTO;
import com.example.myEcomProjectPractice.Models.Order;

import jakarta.transaction.Transactional;

public interface OrderService {
    
    @Transactional
    public OrderDTO createOrder(Long userId, String address, String phoneNumber);

    public List<OrderDTO> getAllOrders();
    public List<OrderDTO> getUserOrders(Long userId);
    public OrderDTO updateOrderStatus(Long orderId,Order.OrderStatus status);
}
