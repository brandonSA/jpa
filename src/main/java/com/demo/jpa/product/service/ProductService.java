package com.demo.jpa.product.service;

import java.util.UUID;

import org.springframework.data.domain.Page;

import com.demo.jpa.product.dto.request.CreateProductDTO;
import com.demo.jpa.product.dto.request.UpdateProductDTO;
import com.demo.jpa.product.entity.Product;
import com.demo.jpa.product.dto.response.ProductResponseDTO;

public interface ProductService {

  public Product getProductById(UUID id);
  public Page<ProductResponseDTO> getAllProducts(int page, int size);
  public Product createProduct(CreateProductDTO productRequest);
  public Product updateProduct(UpdateProductDTO productUpdateRequest);

}
