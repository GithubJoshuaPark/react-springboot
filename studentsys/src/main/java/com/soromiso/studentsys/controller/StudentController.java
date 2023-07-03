package com.soromiso.studentsys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soromiso.studentsys.model.Student;
import com.soromiso.studentsys.service.StudentService;

@RestController
@RequestMapping("/student")
public class StudentController {

  @Autowired
  private StudentService studentService;

  @PostMapping("/add") // http://localhost:8080/student/add
  public String addStudent(@RequestBody Student student) {
    studentService.saveStudent(student);
    return "new student added";
  }

}
