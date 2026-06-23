package com.demo.jpa.product.util;

import org.mapstruct.Mapper;

import com.demo.jpa.product.dto.request.CreateProductDTO;
import com.demo.jpa.product.dto.response.ProductResponseDTO;
import com.demo.jpa.product.entity.Product;

@Mapper
public interface ProductMapper {
  ProductResponseDTO productToProductResponseDTO(Product product);
  Product createProductDTOToProduct(CreateProductDTO productRequest);
}
