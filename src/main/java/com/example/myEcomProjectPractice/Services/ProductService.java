package com.example.myEcomProjectPractice.Services;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.myEcomProjectPractice.DTO.ProductDTO;
import com.example.myEcomProjectPractice.DTO.ProductListDTO;

import jakarta.transaction.Transactional;

public interface ProductService {
 
    @Transactional
    public ProductDTO createProduct(ProductDTO productDTO,MultipartFile image) throws IOException;

    @Transactional
    public ProductDTO updateProduct(Long id,ProductDTO productDTO,MultipartFile image) throws IOException;

    @Transactional
    public void deleteProduct(Long id);

    @Transactional
    public ProductDTO getProduct(Long id);

    @Transactional
    public List<ProductListDTO> getAllProducts();
}
