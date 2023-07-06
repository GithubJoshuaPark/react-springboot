package com.soromiso.jwtex.service;

import java.util.List;

import com.soromiso.jwtex.domain.user.User;

public interface UserService {
    public User save(User user);
    public List<User> getAllUsers();
    public boolean existsById(Long id);
    public User getUserById(Long id);
    public User updateUser(Long id, User user);
    public void deleteUser(Long id);
}
