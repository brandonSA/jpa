package com.demo.jpa.service;

import java.util.UUID;

import org.springframework.data.domain.Page;

import com.demo.jpa.dto.request.ProductRequestDTO;
import com.demo.jpa.dto.response.ProductResponseDTO;
import com.demo.jpa.entity.Product;

public interface ProductService {

  public Product getProductById(UUID id);
  public Page<ProductResponseDTO> getAllProducts(int page, int size);
  public Product createProduct(ProductRequestDTO productRequest);
  public Product updateProduct(UUID id, ProductRequestDTO productRequest);

}
