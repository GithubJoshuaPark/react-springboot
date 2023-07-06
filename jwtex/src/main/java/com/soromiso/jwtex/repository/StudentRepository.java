package com.soromiso.jwtex.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soromiso.jwtex.domain.student.Student;

public interface StudentRepository extends JpaRepository<Student, Long>{
    
}
