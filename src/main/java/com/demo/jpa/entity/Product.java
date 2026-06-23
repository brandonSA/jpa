package com.demo.jpa.entity;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "products")
public class Product {
  
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @NotBlank(message = "Name is required")
  @Size(max = 20)
  private String name;

  @NotNull
  private double price;

  @Size(max = 50)
  private String description;

  // Constructors
  public Product() {}

  public Product(String name, double price) {
    this.name = name;
    this.price = price;
  }

  //Getters and Setters
  public UUID getId() { return id; }
  public void setId(UUID id) { this.id = id; }

  public String getName() { return name; }
  public void setName(String name) { this.name = name; }

  public double getPrice() { return price; }
  public void setPrice(double price) { this.price = price; }

  public String getDescription() { return description; }
  public void setDescription(String description) { this.description = description; }
}
