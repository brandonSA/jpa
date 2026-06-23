package com.demo.jpa.product.service.impl;

import java.util.UUID;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.demo.jpa.product.dto.request.CreateProductDTO;
import com.demo.jpa.product.dto.request.UpdateProductDTO;
import com.demo.jpa.product.dto.response.ProductResponseDTO;
import com.demo.jpa.product.entity.Product;
import com.demo.jpa.product.repository.ProductRepository;
import com.demo.jpa.product.service.ProductService;
import com.demo.jpa.product.util.ProductMapper;
import com.demo.jpa.shared.exception.NotFoundException;

@Service
public class ProductServiceImpl implements ProductService {

  @Autowired
  private ProductRepository productRepository;

  private final ProductMapper productMapper = Mappers.getMapper(ProductMapper.class);

  public Product getProductById(UUID id) {
    return productRepository.findById(id).orElseThrow(() -> new NotFoundException("Product", id));
  }

  public Page<ProductResponseDTO> getAllProducts(int page, int size) {
    Page<ProductResponseDTO> products = productRepository.findAll(PageRequest.of(page, size))
        .map(productMapper::productToProductResponseDTO);
    return products.isEmpty() ? Page.empty() : products;
  }

  public Product createProduct(CreateProductDTO productRequest) {
    Product product = productRepository.save(productMapper.createProductDTOToProduct(productRequest));
    if (product == null) {
      return null;
    }
    return product;
  }

  public Product updateProduct(UpdateProductDTO productUpdateRequest) {
    Product existingProduct = productRepository.findById(productUpdateRequest.id()).orElseThrow(() -> new NotFoundException("Product", productUpdateRequest.id()));

    if (existingProduct == null) {
      return null;
    }

    existingProduct.setName(productUpdateRequest.name());
    existingProduct.setPrice(productUpdateRequest.price());
    existingProduct.setDescription(productUpdateRequest.description());
    Product updatedProduct = productRepository.save(existingProduct);

    return updatedProduct;
  }
}
