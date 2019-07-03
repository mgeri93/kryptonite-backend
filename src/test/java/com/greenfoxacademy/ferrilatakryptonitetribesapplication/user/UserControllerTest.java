package com.greenfoxacademy.ferrilatakryptonitetribesapplication.user;

import com.greenfoxacademy.ferrilatakryptonitetribesapplication.user.dto.UserWithKingdomDTO;
import java.nio.charset.Charset;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.mockito.Mockito.when;

import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

  private MediaType contentType =
      new MediaType(
          MediaType.APPLICATION_JSON.getType(),
          MediaType.APPLICATION_JSON.getSubtype(),
          Charset.forName("utf8"));

  @Mock
  UserRepository userRepository;

  @MockBean
  UserServiceImpl userService;

  @Autowired
  MockMvc mockMvc;

  @Before
  public void init() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void postLoginWithValidCredentials() throws Exception {
    when(userService.loginResponse("Bond", "password123"))
        .thenReturn(new ResponseEntity<>(new User("Bond", "password123"), HttpStatus.OK));
    mockMvc.perform(post("/login")
                .contentType(contentType)
                .content("{\"username\": \"Bond\", \"password\": \"password123\"}"))
        .andDo(print())
        .andExpect(status().isOk());
  }

  @Test
  public void postLoginWithMissingUsername() throws Exception {
    when(userService.loginResponse("", "password123"))
        .thenReturn(new ResponseEntity(HttpStatus.BAD_REQUEST));
    mockMvc.perform(post("/login")
                .contentType(contentType)
                .content("{\"username\":, \"password\": \"password123\"}"))
        .andDo(print())
        .andExpect(status().isBadRequest());
  }

  @Test
  public void postLoginWithNonexistentUser() throws Exception {
    when(userService.loginResponse("Bond", "password123"))
        .thenReturn(new ResponseEntity<>(new User("Bond", "password123"), HttpStatus.UNAUTHORIZED));
    mockMvc.perform(post("/login")
                .contentType(contentType)
                .content("{\"username\": \"Bond\", \"password\": \"password123\"}"))
        .andDo(print())
        .andExpect(status().isUnauthorized());
  }

  @Test
  public void postLoginWithWrongPassword() throws Exception {
    when(userService.loginResponse("Bond", "wrongpassword"))
        .thenReturn(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    mockMvc.perform(post("/login")
                .contentType(contentType)
                .content("{\"username\": \"Bond\", \"password\": \"wrongpassword\"}"))
        .andDo(print())
        .andExpect(status().isBadRequest());
  }

  @Test
  public void postLoginWithSavedUser() throws Exception {
    userRepository.save(new User("Bond", "password123"));
    mockMvc.perform(post("/login")
                .contentType(contentType)
                .content("{\"username\": \"Bond\", \"password\": \"password123\"}"))
        .andDo(print())
        .andExpect(status().isOk());
  }

  @Test
  public void register_unsuccessful_withMissingJsonObject() throws Exception {
    mockMvc.perform(post("/register"))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void registerWithValidCredentials() throws Exception {
    UserDTO testUserDTO = new UserDTO();
    testUserDTO.setUsername("Dani");
    testUserDTO.setPassword("LOL");
    when(userService.registerNewUser(testUserDTO))
        .thenReturn(new ResponseEntity<>(new UserWithKingdomDTO(1, "Dani", 1), HttpStatus.OK));
    mockMvc.perform(post("/register")
        .contentType(contentType)
        .content("{\"id\": \"1\", \"username\": \"Dani\", \"kingdomId\": \"1\"}"))
        .andDo(print())
        .andExpect(status().isOk());
  }
}
