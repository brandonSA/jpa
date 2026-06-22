package com.demo.jpa.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.jpa.exception.NotFoundException;
import com.demo.jpa.mapper.ProductMapper;
import com.demo.jpa.model.Product;
import com.demo.jpa.model.ProductRequestDTO;
import com.demo.jpa.model.ProductResponseDTO;
import com.demo.jpa.model.Result;
import com.demo.jpa.repository.ProductRepository;

@Service
public class ProductService {

  @Autowired
  private ProductRepository productRepository;

  private final ProductMapper productMapper = new ProductMapper();

  public Result<Product> getProductById(UUID id) {
    return productRepository.findById(id).map(Result::ok).orElseThrow(() -> new NotFoundException("Product", id));
  }

  public Result<List<ProductResponseDTO>> getAllProducts() {
    List<ProductResponseDTO> products = productRepository.findAll().stream()
        .map(productMapper::mapToResponseDTO)
        .toList();
    return products.isEmpty() ? Result.ok(List.of()) : Result.ok(products);
  }

  public Result<Product> createProduct(ProductRequestDTO productRequest) {
    Product product = productRepository.save(productMapper.mapToEntity(productRequest));
    return product != null ? Result.ok(product) : Result.failure("Failed to create product");
  }

  public Product updateProduct(UUID id, ProductRequestDTO productRequest) {
    Product existingProduct = productRepository.findById(id).orElseThrow(() -> new NotFoundException("Product", id));

    if (existingProduct == null) {
      return null;
    }

    existingProduct.setName(productRequest.name());
    existingProduct.setPrice(productRequest.price());
    existingProduct.setDescription(productRequest.description());
    Product updatedProduct = productRepository.save(existingProduct);

    return updatedProduct;
  }
}
