package com.demo.jpa.product.dto.response;

import java.util.UUID;

public record ProductResponseDTO(
  UUID id,
  String name,
  double price,
  String description
) {}