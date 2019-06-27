package com.greenfoxacademy.ferrilatakryptonitetribesapplication.user;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserTest {

  @MockBean
  private UserRepository userRepository;

  @MockBean
  UserServiceImpl userService;

  @Autowired
  MockMvc mockMvc;

  @Before
  public void init() {
    MockitoAnnotations.initMocks(this);
    userService = new UserServiceImpl(userRepository);
  }

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
  public void isValidUser() {
    User testUser = new User("geri", "password");
    assertTrue(userService.isValidUser(testUser));
  }

  @Test
  public void notValidUser() {
    User testUser = new User("", "password");
    assertFalse(userService.isValidUser(testUser));
  }

  @Test
  public void isExistingUser() {
    User testUser = new User("geri", "password");
    assertFalse(userService.isExistingUser(testUser));
  }

  @Test
  public void credentialsProvidedWithProvided() {
    assertTrue(userService.credentialsProvided("admin", "password"));
  }

  @Test
  public void validCredentialsWithNonexistentUser () {
    assertFalse(userService.validCredentials("user", "root"));
  }
}
