package com.demo.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.jpa.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {}