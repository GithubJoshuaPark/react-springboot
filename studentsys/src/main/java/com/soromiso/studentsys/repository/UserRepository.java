package com.soromiso.studentsys.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soromiso.studentsys.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
