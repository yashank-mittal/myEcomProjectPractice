package com.example.myEcomProjectPractice.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.myEcomProjectPractice.DTO.ProductListDTO;
import com.example.myEcomProjectPractice.Models.Product;

public interface ProductRepo extends JpaRepository<Product,Long>{
    
    @Query("Select new com.example.myEcomProjectPractice.DTO.ProductListDTO(p.prodId,p.prod_name,p.prod_desc,p.prod_price,p.prod_quantity,p.imageUrl) FROM Product p")
    List<ProductListDTO> findAllWithoutComments(); 
}
