package com.greenfoxacademy.ferrilatakryptonitetribesapplication.applicationuser;

import com.greenfoxacademy.ferrilatakryptonitetribesapplication.kingdom.IKingdomRepository;
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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ApplicationUserTest {

  @MockBean
  private ApplicationUserRepository applicationUserRepository;

  @MockBean
  ApplicationUserServiceImpl userService;

  @MockBean
  IKingdomRepository kingdomRepository;

  @MockBean
  BCryptPasswordEncoder encoder;

  @Autowired
  MockMvc mockMvc;

  @Before
  public void init() {
    MockitoAnnotations.initMocks(this);
    userService = new ApplicationUserServiceImpl(applicationUserRepository, kingdomRepository,
        encoder);
  }

  @Test
  public void getUsername() {
    ApplicationUser testApplicationUser = new ApplicationUser("geri","password");
    assertEquals("geri", testApplicationUser.getUsername());
  }

  @Test
  public void getPassword() {
    ApplicationUser testApplicationUser = new ApplicationUser("geri", "password");
    assertEquals("password", testApplicationUser.getPassword());
  }

  @Test
  public void isValidUser() {
    ApplicationUser testApplicationUser = new ApplicationUser("geri", "password");
    assertTrue(userService.isValidUser(testApplicationUser));
  }

  @Test
  public void notValidUser() {
    ApplicationUser testApplicationUser = new ApplicationUser("", "password");
    assertFalse(userService.isValidUser(testApplicationUser));
  }

  @Test
  public void isExistingUser() {
    ApplicationUser testApplicationUser = new ApplicationUser("geri", "password");
    assertFalse(userService.isExistingUser(testApplicationUser));
  }

  @Test
  public void credentialsProvidedWithProvided() {
    assertTrue(userService.credentialsProvided("admin", "password"));
  }

  @Test
  public void validCredentialsWithNonexistentUser() {
    assertFalse(userService.validCredentials("applicationUser", "root"));
  }
}
