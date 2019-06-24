package com.greenfoxacademy.ferrilatakryptonitetribesapplication.Models.UserTest;

import static org.junit.Assert.*;

import com.greenfoxacademy.ferrilatakryptonitetribesapplication.User.User;
import org.junit.Test;

public class UserTest {

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
}
