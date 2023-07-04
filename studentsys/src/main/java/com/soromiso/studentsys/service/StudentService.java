package com.soromiso.studentsys.service;

import java.util.List;

import com.soromiso.studentsys.model.Student;

public interface StudentService {
  public Student saveStudent(Student student);
  public List<Student> getAllStudents();
}
