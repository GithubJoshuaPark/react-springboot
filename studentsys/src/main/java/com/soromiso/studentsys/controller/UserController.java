package com.soromiso.studentsys.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soromiso.studentsys.exception.UserNotFoundException;
import com.soromiso.studentsys.model.User;
import com.soromiso.studentsys.service.UserService;

// AFFC-B6FD


import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @PostMapping("/add") // http://localhost:9090/api/v1/user/add
  public String addUser(@RequestBody User user) {
    userService.saveUser(user);
    return "new user added";
  }

  @GetMapping("/all") // http://localhost:9090/api/v1/user/all
  public List<User> getAllUsers() {
    return userService.getAllUsers();
  }

  @GetMapping("/get/{id}") // http://localhost:9090/api/v1/user/get/1
  public User getUserById(@PathVariable Long id) {
    return userService.getUserById(id);
  }

  @PutMapping("/update/{id}") // http://localhost:9090/api/v1/user/update
  public String updateUser(@RequestBody User user, @PathVariable Long id) {
    if(!userService.isUserExist(id)) {
      throw new UserNotFoundException(id);
    }
    userService.updateUser(user);
    return "user updated";
  }

  @DeleteMapping("/delete/{id}") // http://localhost:9090/api/v1/user/delete/1
  public String deleteUser(@PathVariable Long id) {
    if(!userService.isUserExist(id)) {
      throw new UserNotFoundException(id);
    }
    userService.deleteUser(id);
    return "user deleted";
  }

}
