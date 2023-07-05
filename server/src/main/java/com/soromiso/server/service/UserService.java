package com.soromiso.server.service;

import com.soromiso.server.model.User;

import java.util.List;

public interface UserService {
    public User saveUser(User user);
    public List<User> getAllUsers();
}
