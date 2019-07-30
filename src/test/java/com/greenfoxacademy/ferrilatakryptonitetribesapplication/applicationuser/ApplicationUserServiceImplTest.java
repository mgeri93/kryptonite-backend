package com.greenfoxacademy.ferrilatakryptonitetribesapplication.applicationuser;

import static org.mockito.Mockito.when;

import com.greenfoxacademy.ferrilatakryptonitetribesapplication.applicationuser.dto.UserWithKingdomDTO;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.exception.customexceptions.UserRelatedException;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.kingdom.Kingdom;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ApplicationUserServiceImplTest {

  @MockBean
  ApplicationUserServiceImpl userService;

  @MockBean
  ApplicationUserRepository applicationUserRepository;

  @Autowired
  MockMvc mockMvc;

  @Autowired
  BCryptPasswordEncoder encoder;

  @Before
  public void init() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void findAllUsersWithExistingUsersTest() {
    List<ApplicationUser> applicationUsers = new ArrayList<>();
    applicationUsers.add(new ApplicationUser("Dani", "pw"));
    applicationUsers.add(new ApplicationUser("Geri", "pass"));
    when(userService.findAllUser())
        .thenReturn(applicationUsers);
    assertEquals(2, userService.findAllUser().size());
  }

  @Test
  public void findAllUsersWithoutExistingUsers() {
    List<ApplicationUser> applicationUsers = new ArrayList<>();
    when(userService.findAllUser())
        .thenReturn(applicationUsers);
    assertEquals(0, userService.findAllUser().size());
  }

  @Test
  public void createUserFromDTOTest() {
    ApplicationUserDTO testUserDTO = new ApplicationUserDTO();
    testUserDTO.setUsername("Dani");
    testUserDTO.setPassword(encoder.encode("LOL"));
    when(userService.createUserFromDTO(testUserDTO))
        .thenReturn(new ApplicationUser("Dani", "LOL"));
    assertEquals("Dani", userService.createUserFromDTO(testUserDTO).getUsername());
    assertEquals("LOL", userService.createUserFromDTO(testUserDTO).getPassword());
  }

  @Test
  public void createKingdomTest() {
    ApplicationUser applicationUser = new ApplicationUser("Dani", "LOL");
    when(userService.createKingdom("", applicationUser))
        .thenReturn(new Kingdom(String.format("%s's kingdom", applicationUser.getUsername()),
        applicationUser));
    assertEquals("Dani's kingdom", userService.createKingdom("", applicationUser).getName());

  }
}
