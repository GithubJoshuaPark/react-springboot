package com.soromiso.studentsys.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.soromiso.studentsys.model.Student;

public interface StudentRepository extends JpaRepository<Student, Integer>{

}
