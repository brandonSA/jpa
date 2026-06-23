package com.demo.jpa.product.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateProductDTO(
  @NotBlank(message = "Name is required") 
  @Size(max = 20)
  String name,
  
  @NotNull(message = "Price is required")
  double price,
  
  @Size(max = 50)
  String description
) {}