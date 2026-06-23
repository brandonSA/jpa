package com.demo.jpa.service.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.demo.jpa.dto.request.ProductRequestDTO;
import com.demo.jpa.dto.response.ProductResponseDTO;
import com.demo.jpa.entity.Product;
import com.demo.jpa.exception.NotFoundException;
import com.demo.jpa.repository.ProductRepository;
import com.demo.jpa.service.ProductService;
import com.demo.jpa.util.ProductMapper;

@Service
public class ProductServiceImpl implements ProductService {

  @Autowired
  private ProductRepository productRepository;

  private final ProductMapper productMapper = new ProductMapper();

  public Product getProductById(UUID id) {
    return productRepository.findById(id).orElseThrow(() -> new NotFoundException("Product", id));
  }

  public Page<ProductResponseDTO> getAllProducts(int page, int size) {
    Page<ProductResponseDTO> products = productRepository.findAll(PageRequest.of(page, size))
        .map(productMapper::mapToResponseDTO);
    return products.isEmpty() ? Page.empty() : products;
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
