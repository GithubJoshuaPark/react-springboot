package com.soromiso.jwtex.service;

import java.util.List;

import com.soromiso.jwtex.domain.user.User;

public interface UserService {
    public User save(User user);
    public List<User> getAllUsers();
    public boolean existsById(Integer id);
    public User getUserById(Integer id);
    public User updateUser(Integer id, User user);
    public void deleteUser(Integer id);
    public User findByEmail(String email);
}
