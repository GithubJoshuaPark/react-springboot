package com.soromiso.jwtex;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.soromiso.jwtex.authService.AuthenticationService;
import com.soromiso.jwtex.domain.student.Student;
import com.soromiso.jwtex.dto.RegisterRequest;
import com.soromiso.jwtex.service.StudentService;

import static com.soromiso.jwtex.domain.enums.Role.*;

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
			student.setGender(ADMIN);
						
			studentService.createStudent(student);
			
			System.out.println("Hello World from Spring Boot 2!");
		};
	}

	@Bean
	public CommandLineRunner commandLineRunner(
			AuthenticationService service
	) {
		return args -> {
			RegisterRequest admin = RegisterRequest.builder()
					.firstname("Admin")
					.lastname("Admin")
					.email("admin@mail.com")
					.password("password")
					.role(ADMIN)
					.build();
			System.out.println("Admin token: " + service.register(admin).getAccessToken());

			RegisterRequest manager = RegisterRequest.builder()
					.firstname("Admin")
					.lastname("Admin")
					.email("manager@mail.com")
					.password("password")
					.role(MANAGER)
					.build();
			System.out.println("Manager token: " + service.register(manager).getAccessToken());

		};
	}

}
