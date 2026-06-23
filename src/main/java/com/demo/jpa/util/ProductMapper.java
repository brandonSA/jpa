package com.demo.jpa.util;

import com.demo.jpa.dto.request.ProductRequestDTO;
import com.demo.jpa.dto.response.ProductResponseDTO;
import com.demo.jpa.entity.Product;

public class ProductMapper {
  
  public ProductResponseDTO mapToResponseDTO(Product product) {
    return new ProductResponseDTO(product.getId(), product.getName(), product.getPrice(), product.getDescription());
  }

  public Product mapToEntity(ProductRequestDTO productRequestDTO) {
    Product product = new Product();
    product.setName(productRequestDTO.name());
    product.setPrice(productRequestDTO.price());
    product.setDescription(productRequestDTO.description());
    return product;
  }
}
