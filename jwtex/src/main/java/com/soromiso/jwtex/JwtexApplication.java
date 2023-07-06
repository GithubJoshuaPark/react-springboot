package com.soromiso.jwtex;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.soromiso.jwtex.domain.enums.Gender;
import com.soromiso.jwtex.domain.enums.Role;
import com.soromiso.jwtex.domain.student.Student;
import com.soromiso.jwtex.service.StudentService;

@SpringBootApplication
public class JwtexApplication {

	public static void main(String[] args) {
		SpringApplication.run(JwtexApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLinerunner(StudentService studentService) {
		return args -> {
			Student student = new Student();
			student.setName("Joshua Park");
			student.setEmail("soromiso@gmail.com");
			student.setGender(Role.ADMIN);
						
			studentService.createStudent(student);
			
			System.out.println("Hello World from Spring Boot 2!");
		};
	}

}
