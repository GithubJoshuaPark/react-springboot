package com.soromiso.studentsys.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.soromiso.studentsys.model.User;
import com.soromiso.studentsys.service.UserServiceImpl;

import lombok.RequiredArgsConstructor;

@SpringBootTest
public class UserRepositoryTest {

  @Autowired
  private UserServiceImpl userServiceImpl;

  @Test
  public void saveAndFindAllTest() {
    User user = new User();
    user.setName("test");
    user.setEmail("soromiso@gmail.com");
    user.setUsername("user name");

    userServiceImpl.saveUser(user);

     var users = userServiceImpl.getAllUsers();
     for (User u : users) {
       System.out.println(u);
     }
  }

}
