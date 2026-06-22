package com.demo.jpa.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.jpa.model.Product;
import com.demo.jpa.model.ProductRequestDTO;
import com.demo.jpa.model.ProductResponseDTO;
import com.demo.jpa.service.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/products")
@Tag(name = "Product Management", description = "Endpoints for managing products")
public class ProductController {

  @Autowired
  private ProductService productService;

  // Get all products
  @GetMapping
  @Operation(summary = "Get all products", description = "Retrieve a list of all products from the product table")
  public ResponseEntity<List<ProductResponseDTO>> getAllProducts() {
    List<ProductResponseDTO> result = productService.getAllProducts();
    return result.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(result);
  }

  // Get product by ID
  @GetMapping("/{id}")
  @Operation(summary = "Get product by ID", description = "Retrieve a product by its ID from the product table")
  public ResponseEntity<Product> getProductById(@Valid @PathVariable UUID id) {
    Product result = productService.getProductById(id);
    return result != null ? ResponseEntity.ok(result) : ResponseEntity.notFound().build();
  }

  // Create product
  @PostMapping
  @Operation(summary = "Create a new product", description = "Add a new product to the product table")
  public ResponseEntity<Product> createProduct(@Valid @RequestBody ProductRequestDTO productRequest) {
    Product result = productService.createProduct(productRequest);
    return result != null ? ResponseEntity.status(HttpStatus.CREATED).body(result)
        : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
  }

  // Update product
  @PutMapping("/{id}")
  @Operation(summary = "Update an existing product", description = "Update the details of an existing product in the product table")
  public ResponseEntity<Product> updateProduct(@Valid @PathVariable("id") final UUID id,
      @Valid @RequestBody final ProductRequestDTO productRequest) {
    Product updatedProduct = productService.updateProduct(id, productRequest);
    return ResponseEntity.status(HttpStatus.OK).body(updatedProduct);
  }
}
