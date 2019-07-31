package com.greenfoxacademy.ferrilatakryptonitetribesapplication.applicationuser;

import com.greenfoxacademy.ferrilatakryptonitetribesapplication.exception.customexceptions.UserRelatedException;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.kingdom.Kingdom;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class ApplicationUserServiceImplTestJunit5 {

  @MockBean
  ApplicationUserServiceImpl userService;

  @MockBean
  ApplicationUserRepository applicationUserRepository;

  @Autowired
  MockMvc mockMvc;

  @Autowired
  BCryptPasswordEncoder encoder;

  @BeforeEach
  void init() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  void registerNewUser() {
    ApplicationUserDTO testUserDTO = new ApplicationUserDTO();
    testUserDTO.setUsername("Dani");
    testUserDTO.setPassword(encoder.encode("LOL"));
    testUserDTO.setKingdom("Kiralysag");

    when(userService.registerNewUser(testUserDTO))
        .thenReturn(new ResponseEntity<>(testUserDTO, HttpStatus.OK));

    assertEquals(HttpStatus.OK, userService.registerNewUser(testUserDTO).getStatusCode());
  }

  @Test
  void registerAlreadyExistingUser() {
    ApplicationUserDTO testUserDTO = new ApplicationUserDTO();
    testUserDTO.setUsername("Dani");
    testUserDTO.setPassword(encoder.encode("LOL"));
    testUserDTO.setKingdom("Kiralysag");

    when(applicationUserRepository.existsByUsername("Dani")).thenReturn(true);
    when(userService.registerNewUser(testUserDTO))
        .thenThrow(new UserRelatedException("Username already taken, choose another one!"));

    assertThrows(UserRelatedException.class, () -> {
      userService.registerNewUser(testUserDTO);
    });
  }

  @Test
  void registerUserWithMissingCredentials() {
    ApplicationUserDTO testUserDTO = new ApplicationUserDTO();
    testUserDTO.setUsername("Dani");
    testUserDTO.setPassword(encoder.encode(""));
    testUserDTO.setKingdom("Kiralysag");

    when(userService.registerNewUser(testUserDTO))
        .thenThrow(new UserRelatedException("Missing parameter: password"));

    assertThrows(UserRelatedException.class, () -> {
      userService.registerNewUser(testUserDTO);
    });
  }

  @Test
  void loginResponseWithValidCredentials() {
    when(userService.loginResponse("Bond", "password123"))
        .thenReturn(new ResponseEntity<>(new ApplicationUser("Bond",
            "password123"), HttpStatus.OK));

    assertEquals(HttpStatus.OK, userService.loginResponse("Bond", "password123").getStatusCode());
  }

  @Test
  void loginResponseWithoutUsername() {
    when(userService.loginResponse("", "password123"))
        .thenThrow(new UserRelatedException("Missing parameter(s): username"));

    assertThrows(UserRelatedException.class, this::loginResponseWithoutUsername);
  }

  @Test
  void loginResponseWithoutPassword() {
    when(userService.loginResponse("Bond", ""))
        .thenThrow(new UserRelatedException("Missing parameter(s): password"));

    assertThrows(UserRelatedException.class, this::loginResponseWithoutPassword);
  }

  @Test
  void getKingdomListByUserId() {
    ApplicationUser applicationUser = new ApplicationUser("Dani", "LOL");
    List<Kingdom> kingdoms = new ArrayList<>();
    kingdoms.add(new Kingdom("FirstKingdom", applicationUser));
    kingdoms.add(new Kingdom("SecondKingdom", applicationUser));
    applicationUser.setKingdoms(kingdoms);

    when(userService.getKingdomListByUserId(applicationUser.getId())).thenReturn(kingdoms);

    assertEquals(2, userService.getKingdomListByUserId(applicationUser.getId()).size());
  }

  @Test
  void getKingdomListWithoutId() {
    when(!applicationUserRepository.existsById(1))
        .thenThrow(new UserRelatedException("No User with this id: "));
    assertThrows(UserRelatedException.class, this::getKingdomListWithoutId);
  }
}
