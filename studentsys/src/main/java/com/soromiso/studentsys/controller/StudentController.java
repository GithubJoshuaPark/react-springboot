package com.soromiso.studentsys.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soromiso.studentsys.model.Student;
import com.soromiso.studentsys.service.StudentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/student")
@RequiredArgsConstructor
public class StudentController {

  private final StudentService studentService;

  @PostMapping("/add") // http://localhost:9090/api/v1/student/add
  public String addStudent(@RequestBody Student student) {
    studentService.saveStudent(student);
    return "new student added";
  }

  @GetMapping("/all") // http://localhost:9090/api/v1/student/all
  public List<Student> getAllStudents() {
    return studentService.getAllStudents();
  }

}
