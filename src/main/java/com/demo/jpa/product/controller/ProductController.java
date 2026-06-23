package com.demo.jpa.product.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.jpa.product.dto.request.CreateProductDTO;
import com.demo.jpa.product.dto.request.UpdateProductDTO;
import com.demo.jpa.product.dto.response.ProductResponseDTO;
import com.demo.jpa.product.entity.Product;
import com.demo.jpa.product.service.ProductService;
import com.demo.jpa.shared.constant.ApiConstants;
import com.demo.jpa.shared.dto.response.ApiResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping(ApiConstants.PRODUCTS_PATH)
@Tag(name = "Product Management", description = "Endpoints for managing products")
public class ProductController {

  @Autowired
  private ProductService productService;

  // Get all products
  @GetMapping
  @Operation(summary = "Get all products", description = "Retrieve a list of all products from the product table")
  public ResponseEntity<ApiResponse<Page<ProductResponseDTO>>> getAllProducts(@RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "10") int size) {
    Page<ProductResponseDTO> result = productService.getAllProducts(page, size);
    return ResponseEntity.ok(ApiResponse.success(result));
  }

  // Get product by ID
  @GetMapping("/{id}")
  @Operation(summary = "Get product by ID", description = "Retrieve a product by its ID from the product table")
  public ResponseEntity<ApiResponse<Product>> getProductById(@Valid @PathVariable UUID id) {
    Product result = productService.getProductById(id);
    return ResponseEntity.ok(ApiResponse.success(result));
  }

  // Create product
  @PostMapping
  @Operation(summary = "Create a new product", description = "Add a new product to the product table")
  public ResponseEntity<ApiResponse<Product>> createProduct(@Valid @RequestBody CreateProductDTO productRequest) {
    Product result = productService.createProduct(productRequest);
    return ResponseEntity.ok(ApiResponse.success(result));
  }

  // Update product
  @PutMapping
  @Operation(summary = "Update an existing product", description = "Update the details of an existing product in the product table")
  public ResponseEntity<ApiResponse<Product>> updateProduct(@Valid @RequestBody final UpdateProductDTO productUpdateRequest) {
    Product updatedProduct = productService.updateProduct(productUpdateRequest);
    return ResponseEntity.ok(ApiResponse.success(updatedProduct));
  }
}
