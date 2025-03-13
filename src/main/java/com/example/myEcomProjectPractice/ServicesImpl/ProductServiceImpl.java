package com.example.myEcomProjectPractice.ServicesImpl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.myEcomProjectPractice.DTO.ProductDTO;
import com.example.myEcomProjectPractice.DTO.ProductListDTO;
import com.example.myEcomProjectPractice.Mapper.ProductMapper;
import com.example.myEcomProjectPractice.Models.Product;
import com.example.myEcomProjectPractice.Repo.ProductRepo;
import com.example.myEcomProjectPractice.Services.ProductService;
import com.example.myEcomProjectPractice.exception.ResourceNotFoundException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepo productRepository;
    private final ProductMapper productMapper;

    private static final String UPLOAD_DIR = "src/main/resources/static/images/";

    @Transactional
    public ProductDTO createProduct(ProductDTO productDTO, MultipartFile image) throws IOException{
        Product product = productMapper.toEntity(productDTO);
        if(image != null && !image.isEmpty()){
            String fileName = saveImage(image);
            product.setImage("/images/"+fileName);
        }
        Product savedProduct = productRepository.save(product);
        return productMapper.toDTO(savedProduct);
    }

    @Transactional
    public ProductDTO updateProduct(Long id, ProductDTO productDTO, MultipartFile image) throws IOException{
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Product not found"));
        existingProduct.setName(productDTO.getName());
        existingProduct.setDescription(productDTO.getDescription());
        existingProduct.setPrice(productDTO.getPrice());
        existingProduct.setQuantity(productDTO.getQuantity());
        if(image != null && !image.isEmpty()){
            String fileName = saveImage(image);
            existingProduct.setImage("/images/" + fileName);
        }
        Product updatedProduct = productRepository.save(existingProduct);
        return productMapper.toDTO(updatedProduct);
    }

    @Transactional
    public void deleteProduct(Long id){
        if(!productRepository.existsById(id)){
            throw new ResourceNotFoundException("Product not found");
        }
        productRepository.deleteById(id);
    }

    public ProductDTO getProduct(Long id){
        Product product = productRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Product not found"));
        return productMapper.toDTO(product);
    }

    public Page<ProductListDTO> getAllProducts(Pageable pageable) {
        return productRepository.findAllWithoutComments(pageable);
    }
    private String saveImage(MultipartFile image) throws  IOException{
        String fileName = UUID.randomUUID().toString()+"_"+image.getOriginalFilename();
        Path path = Paths.get(UPLOAD_DIR + fileName);
        Files.createDirectories(path.getParent());
        Files.write(path, image.getBytes());
        return fileName;
    }
}