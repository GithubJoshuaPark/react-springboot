package com.soromiso.jwtex.service;

import java.util.List;

import com.soromiso.jwtex.domain.student.Student;

public interface StudentService {
    public Student createStudent(Student student);
    public List<Student> getAllStudents();
    public boolean existsById(Long id);
    public Student getStudentById(Long id);
    public Student updateStudent(Long id, Student student);
    public void deleteStudent(Long id);
}
