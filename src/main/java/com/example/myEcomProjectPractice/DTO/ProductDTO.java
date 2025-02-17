package com.example.myEcomProjectPractice.DTO;

import java.math.BigDecimal;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class ProductDTO {
    private Long prodId;
    @NotBlank(message = "Product name is required")
    private String prod_name;
    @NotBlank(message = "Product Description is required")
    private String prod_desc;
    @Positive(message = "Product price must be positive")
    private BigDecimal prod_price;
    @PositiveOrZero(message = "Product quantity must be positive or zero")
    private Integer prod_quantity;
    private List<CommentDTO> comments;
    private String imageUrl;
}
