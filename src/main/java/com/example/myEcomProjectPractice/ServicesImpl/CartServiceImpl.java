package com.example.myEcomProjectPractice.ServicesImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.example.myEcomProjectPractice.DTO.CartDTO;
import com.example.myEcomProjectPractice.Mapper.CartMapper;
import com.example.myEcomProjectPractice.Models.Cart;
import com.example.myEcomProjectPractice.Models.CartItem;
import com.example.myEcomProjectPractice.Models.Product;
import com.example.myEcomProjectPractice.Models.User;
import com.example.myEcomProjectPractice.Repo.CartRepo;
import com.example.myEcomProjectPractice.Repo.ProductRepo;
import com.example.myEcomProjectPractice.Repo.UserRepo;
import com.example.myEcomProjectPractice.Services.CartService;
import com.example.myEcomProjectPractice.exception.InsufficientStockException;
import com.example.myEcomProjectPractice.exception.ResourceNotFoundException;

import java.util.ArrayList;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartRepo cartRepository;
    private final ProductRepo productRepository;
    private final UserRepo userRepository;
    private final CartMapper cartMapper;

    public CartDTO addToCart(Long userId, Long productId, Integer quantity){
        User user = userRepository.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("User not found"));
        Product product = productRepository.findById(productId)
                .orElseThrow(()->new ResourceNotFoundException("Product not found"));

        if(product.getQuantity()<quantity){
            throw new InsufficientStockException("Not enough available");
        }

        Cart cart = cartRepository.findByUserId(userId)
                .orElse(new Cart(null, user, new ArrayList<>()));
        Optional<CartItem> existingCartItem = cart.getItems().stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst();

        if(existingCartItem.isPresent()){
            CartItem cartItem = existingCartItem.get();
            cartItem.setQuantity(cartItem.getQuantity()+quantity);
        }else{
            CartItem cartItem = new CartItem(null, cart, product, quantity);
            cart.getItems().add(cartItem);
        }
        Cart savedCart = cartRepository.save(cart);
        return cartMapper.toDTO(savedCart);

    }

    public CartDTO getCart(Long userId){
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(()->new ResourceNotFoundException("Cart not found"));

        return cartMapper.toDTO(cart);
    }
    public void clearCart(Long userId){
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(()->new ResourceNotFoundException("Cart not found"));

        cart.getItems().clear();
        cartRepository.save(cart);
    }

    //update
    public void removeCartItem(Long userId, Long productId) {
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Cart not found for user"));

        cart.getItems().removeIf(item -> item.getProduct().getId().equals(productId));

        cartRepository.save(cart);
    }
}
