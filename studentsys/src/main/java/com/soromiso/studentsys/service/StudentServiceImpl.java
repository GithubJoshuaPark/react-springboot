package com.soromiso.studentsys.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.soromiso.studentsys.model.Student;
import com.soromiso.studentsys.repository.StudentRepository;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService{

  private final StudentRepository studentRepository;

  @Override
  public Student saveStudent(Student student) {
    System.out.println("student: " + student);

    return studentRepository.save(student);
  }

  @Override
  public List<Student> getAllStudents() {
    return studentRepository.findAll();
  }

}
