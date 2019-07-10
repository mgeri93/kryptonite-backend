package com.greenfoxacademy.ferrilatakryptonitetribesapplication.user;

import com.greenfoxacademy.ferrilatakryptonitetribesapplication.exception.customexceptions.UserRelatedException;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.kingdom.IKingdomRepository;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.user.dto.UserWithKingdomDTO;
import java.nio.charset.Charset;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;

import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
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

  private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
      MediaType.APPLICATION_JSON.getSubtype(),
      Charset.forName("utf8"));

  @Mock
  UserRepository userRepository;

  @InjectMocks
  UserServiceImpl userService;

  @Autowired
  MockMvc mockMvc;

  @Mock
  IKingdomRepository kingdomRepository;

  @Before
  public void init() {
    MockitoAnnotations.initMocks(this);
  }

  @Test(expected = UserRelatedException.class)
  public void postLoginWithValidCredentials() throws Exception {
    when(userService.loginResponse("Bond", "password123", "/login"))
        .thenReturn(new ResponseEntity<>(new User("Bond", "password123"),
            HttpStatus.OK));
    mockMvc.perform(post("/login")
        .contentType(contentType)
        .content("{\"username\": \"Bond\", \"password\": \"password123\"}"))
        .andDo(print())
        .andExpect(status().isOk());
  }

  @Test(expected = UserRelatedException.class)
  public void postLoginWithMissingUsername() throws Exception {
    when(userService.loginResponse("", "password123", "/login"))
        .thenThrow(new UserRelatedException("Missing parameter(s): username", "/login"));
    mockMvc.perform(post("/login")
        .contentType(contentType)
        .content("{\"username\": \"\", \"password\": \"password123\"}"))
        .andDo(print())
        .andExpect(status().isBadRequest());
  }

  @Test (expected = UserRelatedException.class)
  public void postLoginWithNonexistentUser() throws Exception {
    when(userService.loginResponse("Bond", "password123", "/login"))
        .thenThrow(new UserRelatedException("No such user in database, please register first",
            "/login"));
    mockMvc.perform(post("/login")
        .contentType(contentType)
        .content("{\"username\": \"Bond\", \"password\": \"password123\"}"))
        .andDo(print())
        .andExpect(status().isBadRequest());
  }

  @Test(expected = UserRelatedException.class)
  public void postLoginWithWrongPassword() throws Exception {
    when(userService.loginResponse("Bond", "wrongpassword", "/login"))
        .thenThrow(new UserRelatedException("Invalid password, please try to log-in again.",
            "/login"));
    mockMvc.perform(post("/login")
        .contentType(contentType)
        .content("{\"username\": \"Bond\", \"password\": \"wrongpassword\"}"))
        .andDo(print())
        .andExpect(status().isBadRequest());
  }

  /*@Test
  public void postLoginWithSavedUser() throws Exception {
    userRepository.save(new User("Bond", "password123"));
    mockMvc.perform(post("/login")
        .contentType(contentType)
        .content("{\"username\": \"Bond\", \"password\": \"password123\"}"))
        .andDo(print())
        .andExpect(status().isOk());
  }*/

  @Test(expected = UserRelatedException.class)
  public void registerWithoutUsernameAndPassword() throws Exception {
    when(userService.registerNewUser(new UserDTO(), "/register"))
        .thenReturn(new ResponseEntity<>("\"status\": \"error\",\n"
            + "    \"message\": \"Missing parameters: username, password!\"",
            HttpStatus.BAD_REQUEST));
    mockMvc.perform(post("/register")
        .contentType(contentType)
        .content("{\"id\": \"\", \"username\": \"\", \"kingdomId\": \"\"}"))
        .andDo(print())
        .andExpect(status().isBadRequest());
  }

  @Test
  public void registerWithValidCredentials() throws Exception {
    UserDTO testUserDTO = new UserDTO();
    testUserDTO.setUsername("Dani");
    testUserDTO.setPassword("LOL");
    when(userService.registerNewUser(testUserDTO, "/register"))
        .thenReturn(new ResponseEntity<>(new UserWithKingdomDTO(1, "Dani", 1), HttpStatus.OK));
    mockMvc.perform(post("/register")
        .contentType(contentType)
        .content("{\"username\": \"Dani\", \"password\": \"kutya\"}"))
        .andDo(print())
        .andExpect(status().isOk());
  }

  public void register_unsuccessful_withMissingJsonObject() throws Exception {
    mockMvc.perform(post("/register"))
        .andExpect(status().isBadRequest());
  }
}
