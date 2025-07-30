package com.example.recipemanagementapi1.repository;

import com.example.recipemanagementapi1.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);



}
