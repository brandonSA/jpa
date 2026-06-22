package com.demo.jpa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.jpa.model.User;
import com.demo.jpa.repository.UserRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/users")
@Tag(name = "User Management", description = "Endpoints for managing users")
public class UserController {

  @Autowired
  private UserRepository userRepository;

  // Get all users
  @GetMapping
  @Operation(summary = "Get all users", description = "Retrieve a list of all users from the user table")
  public List<User> getAllUsers() {
    return userRepository.findAll();
  }
}
