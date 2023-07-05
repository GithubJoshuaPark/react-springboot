package com.soromiso.studentsys.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.soromiso.studentsys.model.User;
import com.soromiso.studentsys.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

  private final UserRepository userRepository;

  @Override
  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  @Override
  public User saveUser(User user) {
    return userRepository.save(user);
  }

}
