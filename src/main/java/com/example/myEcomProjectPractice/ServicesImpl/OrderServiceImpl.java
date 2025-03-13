package com.example.myEcomProjectPractice.ServicesImpl;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Service;

import com.example.myEcomProjectPractice.DTO.CartDTO;
import com.example.myEcomProjectPractice.DTO.OrderDTO;
import com.example.myEcomProjectPractice.Mapper.CartMapper;
import com.example.myEcomProjectPractice.Mapper.OrderMapper;
import com.example.myEcomProjectPractice.Models.Cart;
import com.example.myEcomProjectPractice.Models.Order;
import com.example.myEcomProjectPractice.Models.OrderItem;
import com.example.myEcomProjectPractice.Models.Product;
import com.example.myEcomProjectPractice.Models.User;
import com.example.myEcomProjectPractice.Repo.OrderRepo;
import com.example.myEcomProjectPractice.Repo.ProductRepo;
import com.example.myEcomProjectPractice.Repo.UserRepo;
import com.example.myEcomProjectPractice.Services.CartService;
import com.example.myEcomProjectPractice.Services.EmailService;
import com.example.myEcomProjectPractice.Services.OrderService;
import com.example.myEcomProjectPractice.exception.InsufficientStockException;
import com.example.myEcomProjectPractice.exception.ResourceNotFoundException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final Logger logger = LoggerFactory.getLogger(OrderService.class);

    private final OrderRepo orderRepository;
    private final CartService cartService;
    private final ProductRepo productRepository;
    private final UserRepo userRepository;
    private final EmailService emailService;
    private final OrderMapper orderMapper;
    private final CartMapper cartMapper;

    @Transactional
    public OrderDTO createOrder(Long userId, String address, String phoneNumber){
        User user = userRepository.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("User not found"));
        if(!user.isEmailConfirmation()){
            throw new IllegalStateException("Email not confirmed. Please confirm email before placing order");
        }
        CartDTO cartDTO = cartService.getCart(userId);
        Cart cart = cartMapper.toEntity(cartDTO);

        if(cart.getItems().isEmpty()){
            throw new IllegalStateException("Cannot create an order with an empty cart");
        }

        Order order = new Order();
        order.setUser(user);
        order.setAddress(address);
        order.setPhoneNumber(phoneNumber);
        order.setStatus(Order.OrderStatus.PREPARING);
        order.setCreatedAt(LocalDateTime.now());

        List<OrderItem> orderItems = createOrderItems(cart, order);
        order.setItems(orderItems);

        Order savedOrder = orderRepository.save(order);
        cartService.clearCart(userId);

        try{
            emailService.sendOrderConfirmation(savedOrder);
        }catch (MailException e){
            logger.error("Failed to send order confirmation email for order ID "+savedOrder.getId(), e);
        }
        return orderMapper.toDTO(savedOrder);
    }

    private List<OrderItem> createOrderItems(Cart cart, Order order){
        return cart.getItems().stream().map(cartItem -> {
            Product product = productRepository.findById(cartItem.getProduct().getId())
                    .orElseThrow(()-> new EntityNotFoundException("Product not found with id: "+cartItem.getProduct().getId()));

            if(product.getQuantity() == null){
                throw new IllegalStateException("Product quantity is not set for product "+product.getName());
            }
            if(product.getQuantity() < cartItem.getQuantity()){
                throw new InsufficientStockException("Not enough stock for product "+product.getName());
            }
            product.setQuantity(product.getQuantity() - cartItem.getQuantity());
            productRepository.save(product);

            return new OrderItem(null, order, product, cartItem.getQuantity(), product.getPrice());
        }).collect(Collectors.toList());
    }

    public List<OrderDTO> getAllOrders(){
        return orderMapper.toDTOs(orderRepository.findAll());
    }

    public List<OrderDTO> getUserOrders(Long userId){
        return orderMapper.toDTOs(orderRepository.findByUserId(userId));
    }

    public OrderDTO updateOrderStatus(Long orderId,Order.OrderStatus status){
        Order order = orderRepository.findById(orderId)
                .orElseThrow(()->new ResourceNotFoundException("Order not found"));
        order.setStatus(status);
        Order updatedOrder = orderRepository.save(order);
        return orderMapper.toDTO(updatedOrder);
    }
}