package com.pooja.lms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pooja.lms.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
