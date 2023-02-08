package com.climbing_log.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.climbing_log.model.User;

public interface UserRepository extends JpaRepository<User, String>{ 
    Optional<User> findByUsername(String username); 
}
