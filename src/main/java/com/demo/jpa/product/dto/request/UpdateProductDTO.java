package com.demo.jpa.product.dto.request;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UpdateProductDTO(
  @NotNull(message = "ID is required")
  UUID id,

  @NotBlank(message = "Name is required") 
  @Size(max = 20)
  String name,
  
  @NotNull(message = "Price is required")
  double price,
  
  @Size(max = 50)
  String description
) {}
