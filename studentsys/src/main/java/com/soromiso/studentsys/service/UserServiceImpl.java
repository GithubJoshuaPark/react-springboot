package com.soromiso.studentsys.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.soromiso.studentsys.exception.UserNotFoundException;
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

  @Override
  public void deleteUser(Long id) {
    userRepository.deleteById(id);
  }

  @Override
  public User updateUser(User user) {
    return userRepository.save(user);
  }

  @Override
  public User getUserById(Long id) {
    return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
  }

  @Override
  public boolean isUserExist(Long id) {
    return userRepository.existsById(id);
  }

}
