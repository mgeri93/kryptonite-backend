package com.greenfoxacademy.ferrilatakryptonitetribesapplication.UserTest;

import static org.junit.Assert.*;

import com.greenfoxacademy.ferrilatakryptonitetribesapplication.User.User;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.User.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserTest {

  @Autowired
  UserService userService;

  private User testUser = new User ("geri","password");



  @Test
  public void getUsername() {
    User testUser = new User("geri","password");
    assertEquals("geri",testUser.getUsername());
  }

  @Test
  public void getPassword() {
    User testUser = new User("geri", "password");
    assertEquals("password",testUser.getPassword());
  }

  @Test
  public void isValidUser(){
    User testUser = new User ("geri","password");
    assertTrue(userService.isValidUser(testUser));
  }

  @Test
  public void notValidUser(){
    User testUser = new User ("","password");
    assertFalse(userService.isValidUser(testUser));
  }

}
