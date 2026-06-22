package com.demo.jpa.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String username;
  private String name;
  private String surname;

  @Column(unique = true)
  private String email;

  // Constructors
  public User() {}

  public User(String username, String name, String surname, String email) {
    this.username = username;
    this.name = name;
    this.surname = surname;
    this.email = email;
  }

  // Getters and Setters
  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }

  public String getUsername() { return username; }
  public void setUsername(String username) { this.username = username; }

  public String getName() { return name; }
  public void setName(String name) { this.name = name; }

  public String getSurname() { return surname; }
  public void setSurname(String surname) { this.surname = surname; }

  public String getEmail() { return email; }
  public void setEmail(String email) { this.email = email; }
}