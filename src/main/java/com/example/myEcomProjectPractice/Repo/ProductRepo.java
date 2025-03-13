package com.example.myEcomProjectPractice.Repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.myEcomProjectPractice.DTO.ProductListDTO;
import com.example.myEcomProjectPractice.Models.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {
    @Query("SELECT new com.example.myEcomProjectPractice.DTO.ProductListDTO(p.id, p.name, p.description, p.price, p.quantity, p.image) FROM Product p")
    Page<ProductListDTO> findAllWithoutComments(Pageable pageable);
}
