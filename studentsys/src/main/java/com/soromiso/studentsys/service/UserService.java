package com.soromiso.studentsys.service;

import java.util.List;

import com.soromiso.studentsys.model.User;

public interface UserService {
  public User saveUser(User user);
  public List<User> getAllUsers();
}
