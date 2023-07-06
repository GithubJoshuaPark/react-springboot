package com.soromiso.jwtex.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.soromiso.jwtex.domain.student.Student;
import com.soromiso.jwtex.exceptions.StudentNotFoundException;
import com.soromiso.jwtex.repository.StudentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService{

    private final StudentRepository studentRepository;

    @Override
    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public boolean existsById(Long id) {
        return studentRepository.existsById(id);
    }

    @Override
    public Student getStudentById(Long id) {
        return studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException(id));
    }

    @Override
    public Student updateStudent(Long id, Student student) {
        if(!studentRepository.existsById(id)) throw new StudentNotFoundException(id);
        return studentRepository.save(student);
    }

    @Override
    public void deleteStudent(Long id) {
        if(!studentRepository.existsById(id)) throw new StudentNotFoundException(id);
        studentRepository.deleteById(id);
    }
    
}
