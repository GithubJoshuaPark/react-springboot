package com.soromiso.jwtex.exceptions;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(Integer id) {
        super("User with id " + id + " not found");
    }
}
