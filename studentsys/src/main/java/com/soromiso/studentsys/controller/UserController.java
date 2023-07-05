package com.soromiso.studentsys.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soromiso.studentsys.model.User;
import com.soromiso.studentsys.repository.UserRepository;

// AFFC-B6FD


import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
public class UserController {

  private final UserRepository userRepository;

  @PostMapping("/add") // http://localhost:9090/api/v1/user/add
  public String addUser(@RequestBody User user) {
    userRepository.save(user);
    return "new user added";
  }

  @GetMapping("/all") // http://localhost:9090/api/v1/user/all
  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

}
