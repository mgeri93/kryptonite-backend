package com.greenfoxacademy.ferrilatakryptonitetribesapplication.user;

import static org.junit.Assert.assertEquals;

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
