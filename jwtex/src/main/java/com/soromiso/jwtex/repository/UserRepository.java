package com.soromiso.jwtex.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soromiso.jwtex.domain.user.User;

public interface UserRepository extends JpaRepository<User, Long>{
    Optional<User> findByEmail(String email);
}
