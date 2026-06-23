package com.demo.jpa.product.util;

import com.demo.jpa.product.dto.request.CreateProductDTO;
import com.demo.jpa.product.dto.response.ProductResponseDTO;
import com.demo.jpa.product.entity.Product;

public class ProductMapper {
  
  public ProductResponseDTO mapToResponseDTO(Product product) {
    return new ProductResponseDTO(product.getId(), product.getName(), product.getPrice(), product.getDescription());
  }

  public Product mapToEntity(CreateProductDTO productRequest) {
    Product product = new Product();
    product.setName(productRequest.name());
    product.setPrice(productRequest.price());
    product.setDescription(productRequest.description());
    return product;
  }
}
