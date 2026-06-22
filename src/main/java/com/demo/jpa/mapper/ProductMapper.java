package com.demo.jpa.mapper;

import com.demo.jpa.model.Product;
import com.demo.jpa.model.ProductRequestDTO;
import com.demo.jpa.model.ProductResponseDTO;

public class ProductMapper {
  
  public ProductResponseDTO mapToResponseDTO(Product product) {
    return new ProductResponseDTO(product.getId(), product.getName(), product.getPrice());
  }

  public Product mapToEntity(ProductRequestDTO productRequestDTO) {
    Product product = new Product();
    product.setName(productRequestDTO.name());
    product.setPrice(productRequestDTO.price());
    product.setDescription(productRequestDTO.description());
    return product;
  }
}
