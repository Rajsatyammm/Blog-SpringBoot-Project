package com.satyam.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.satyam.model.User;

public interface IUserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
}
