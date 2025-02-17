package com.example.myEcomProjectPractice.ServicesImpl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.myEcomProjectPractice.DTO.ProductDTO;
import com.example.myEcomProjectPractice.DTO.ProductListDTO;
import com.example.myEcomProjectPractice.Mapper.ProductMapper;
import com.example.myEcomProjectPractice.Models.Product;
import com.example.myEcomProjectPractice.Repo.ProductRepo;
import com.example.myEcomProjectPractice.Services.ProductService;
import com.example.myEcomProjectPractice.exception.ResourceNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepo productRepo;
    private final ProductMapper productMapper;

    private static final String UPLOAD_DIR = "src/main/resources/static/images/";

    @Override
    public ProductDTO createProduct(ProductDTO productDTO, MultipartFile image) throws IOException {
        Product product = productMapper.toProductEntity(productDTO);
        if(image !=null && !image.isEmpty())
        {
            String fileName = saveImage(image);
            product.setImageUrl("/images/"+fileName);
        }
        Product savedProduct = productRepo.save(product);
        return productMapper.toProductDTO(savedProduct);
    }

    @Override
    public ProductDTO updateProduct(Long id, ProductDTO productDTO, MultipartFile image) throws IOException {
        Product existingProduct = productRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        existingProduct.setProd_name(productDTO.getProd_name());
        existingProduct.setProd_desc(productDTO.getProd_desc());
        existingProduct.setProd_price(productDTO.getProd_price());
        existingProduct.setProd_quantity(productDTO.getProd_quantity());
        if(image !=null && !image.isEmpty())
        {
            String fileName = saveImage(image);
            existingProduct.setImageUrl("/images/"+fileName);
        }
        Product updatedProduct = productRepo.save(existingProduct);
        return productMapper.toProductDTO(updatedProduct);
    }

    @Override
    public void deleteProduct(Long id) {
        if(!productRepo.existsById(id))
        {
            throw new ResourceNotFoundException("Product not found");
        }
        productRepo.deleteById(id);
    }

    @Override
    public ProductDTO getProduct(Long id) {
        Product product = productRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        return productMapper.toProductDTO(product);
    }

    @Override
    public List<ProductListDTO> getAllProducts() {
        return productRepo.findAllWithoutComments();
    }
    
    private String saveImage(MultipartFile image) throws IOException {
        String fileName = UUID.randomUUID().toString() + "_" + image.getOriginalFilename();
        Path filePath = Paths.get(UPLOAD_DIR+fileName);
        Files.createDirectories(filePath.getParent());
        Files.write(filePath, image.getBytes());
        return fileName;
    }
}
