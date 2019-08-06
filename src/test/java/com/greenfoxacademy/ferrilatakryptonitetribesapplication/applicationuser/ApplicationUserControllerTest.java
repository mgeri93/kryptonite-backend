package com.greenfoxacademy.ferrilatakryptonitetribesapplication.applicationuser;

import com.greenfoxacademy.ferrilatakryptonitetribesapplication.applicationuser.dto.UserWithKingdomDTO;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.exception.customexceptions.UserRelatedException;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.kingdom.IKingdomRepository;
import java.nio.charset.Charset;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.ArgumentMatchers.any;
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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ApplicationUserControllerTest {

  private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
      MediaType.APPLICATION_JSON.getSubtype(),
      Charset.forName("utf8"));

  @Mock
  ApplicationUserRepository applicationUserRepository;

  @MockBean
  ApplicationUserServiceImpl userService;

  @Autowired
  BCryptPasswordEncoder encoder;

  @Autowired
  MockMvc mockMvc;

  @Mock
  IKingdomRepository kingdomRepository;

  @Before
  public void init() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void postLoginWithValidCredentials() throws Exception {
    when(userService.loginResponse("Bond", "password123"))
        .thenReturn(new ResponseEntity<>(new ApplicationUser("Bond",
            "password123"), HttpStatus.OK));
    mockMvc.perform(post("/login")
        .contentType(contentType)
        .content("{\"username\": \"Bond\", \"password\": \"password123\"}"))
        .andDo(print())
        .andExpect(status().isUnauthorized());
  }

  @Test
  public void postLoginWithMissingUsername() throws Exception {
    when(userService.loginResponse(" ", "password123"))
        .thenReturn(new ResponseEntity(HttpStatus.BAD_REQUEST));
    mockMvc.perform(post("/login")
        .contentType(contentType)
        .content("{\"username\": \"\", \"password\": \"password123\"}"))
        .andDo(print())
        .andExpect(status().isUnauthorized());
  }

  @Test
  public void postLoginWithNonexistentUser() throws Exception {
    when(userService.loginResponse("Bond", "password123"))
        .thenReturn(new ResponseEntity<>(new ApplicationUser("Bond",
            "password123"), HttpStatus.UNAUTHORIZED));
    mockMvc.perform(post("/login")
        .contentType(contentType)
        .content("{\"username\": \"Bond\", \"password\": \"password123\"}"))
        .andDo(print())
        .andExpect(status().isUnauthorized());
  }

  @Test
  public void postLoginWithWrongPassword() throws Exception {
    when(userService.loginResponse("Bond", "wrongpassword"))
        .thenThrow(new UserRelatedException("Invalid password, please try to log-in again."));
    mockMvc.perform(post("/login")
        .contentType(contentType)
        .content("{\"username\": \"Bond\", \"password\": \"wrongpassword\"}"))
        .andDo(print())
        .andExpect(status().isUnauthorized());
  }

  @Test
  public void registerWithoutUsernameAndPassword() throws Exception {
    when(userService.registerNewUser(new ApplicationUserDTO()))
        .thenReturn(new ResponseEntity<>("\"status\": \"error\",\n"
            + "    \"message\": \"Missing parameters: username, password!\"",
            HttpStatus.BAD_REQUEST));
    mockMvc.perform(post("/register")
        .contentType(contentType)
        .content("{\"id\": \"\", \"username\": \"\", \"kingdomId\": \"\"}"))
        .andDo(print())
        .andExpect(status().isOk());
  }

  @Test
  public void registerWithValidCredentials() throws Exception {
    String result = new JSONObject()
        .put("id", 1)
        .put("username", "Dani")
        .put("kingdomId", 1)
        .toString();

    when(userService.registerNewUser(any(ApplicationUserDTO.class)))
        .thenReturn(ResponseEntity.status(200).body(new UserWithKingdomDTO(1, "Dani", 1)));
    mockMvc.perform(post("/register")
        .contentType(contentType)
        .content("{\"username\": \"Dani\", \"password\": \"kutya\"}"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().contentType(contentType))
        .andExpect(content().json(result));
  }
}
