package com.example.myEcomProjectPractice.Mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.myEcomProjectPractice.DTO.OrderDTO;
import com.example.myEcomProjectPractice.DTO.OrderItemDto;
import com.example.myEcomProjectPractice.Models.OrderItem;
import com.example.myEcomProjectPractice.Models.Orders;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mapping(source = "user.userId",target = "userId")
    @Mapping(source = "orderItem", target = "orderItem")
    OrderDTO toDto(Orders orders);

    @Mapping(source = "userId", target = "user.userId")
    @Mapping(source = "orderItem", target = "orderItem") 
    Orders toEntity(OrderDTO orderDTO);

    List<OrderDTO> toDtoList(List<Orders> ordersList);
    List<Orders> toEntityList(List<OrderDTO> orderDTOList);
    
    @Mapping(source = "product.prodId", target = "productId")
    OrderItemDto tOrderItemDto(OrderItem orderItem);

    @Mapping(source = "productId", target = "product.prodId")
    OrderItem toOrderItemEntity(OrderItemDto orderItemDto);

    List<OrderItemDto> tOrderItemDtos(List<OrderItem> orderItems);  
    List<OrderItem> toOrderItemEntities(List<OrderItemDto> orderItemDtos);
}
