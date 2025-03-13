package com.example.myEcomProjectPractice.Services;

import com.example.myEcomProjectPractice.DTO.CartDTO;

public interface CartService {
    public CartDTO addToCart(Long userId, Long productId,Integer quantity);
    public CartDTO getCart(Long userId);
    public void clearCart(Long userId);
    void removeCartItem(Long userId, Long productId);
}
