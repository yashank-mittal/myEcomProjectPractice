package com.example.myEcomProjectPractice.DTO;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductListDTO {
    private Long prodId;
    @NotBlank(message = "Product name is required")
    private String prod_name;
    @NotBlank(message = "Product Description is required")
    private String prod_desc;
    @Positive(message = "Product price must be positive")
    private BigDecimal prod_price;
    @PositiveOrZero(message = "Product quantity must be positive or zero")
    private Integer prod_quantity;
    private String imageUrl;
}
