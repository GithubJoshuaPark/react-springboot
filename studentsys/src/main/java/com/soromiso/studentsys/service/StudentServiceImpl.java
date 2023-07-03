package com.soromiso.studentsys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soromiso.studentsys.model.Student;
import com.soromiso.studentsys.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService{

  @Autowired
  private StudentRepository studentRepository;

  @Override
  public Student saveStudent(Student student) {
    System.out.println("student: " + student);

    return studentRepository.save(student);
  }

}
