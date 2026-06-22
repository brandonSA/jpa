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
import com.demo.jpa.repository.ProductRepository;

@Service
public class ProductService {

  @Autowired
  private ProductRepository productRepository;

  private final ProductMapper productMapper = new ProductMapper();

  public Product getProductById(UUID id) {
    return productRepository.findById(id).orElseThrow(() -> new NotFoundException("Product", id));
  }

  public List<ProductResponseDTO> getAllProducts() {
    List<ProductResponseDTO> products = productRepository.findAll().stream()
        .map(productMapper::mapToResponseDTO)
        .toList();
    return products.isEmpty() ? List.of() : products;
  }

  public Product createProduct(ProductRequestDTO productRequest) {
    Product product = productRepository.save(productMapper.mapToEntity(productRequest));
    if (product == null) {
      return null;
    }
    return product;
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
