package com.demo.jpa.model;

import java.util.UUID;

public record ProductResponseDTO(
  UUID id,
  String name,
  double price
) {}