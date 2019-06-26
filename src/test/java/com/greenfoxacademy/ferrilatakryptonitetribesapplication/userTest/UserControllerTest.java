package com.greenfoxacademy.ferrilatakryptonitetribesapplication.userTest;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

  @Autowired
  MockMvc mockMvc;

  @Test
  public void givenLoginURL_whenMockMVC_thenStatusOK_andReturnsWithLogin() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.post("/login"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().string("login"));
  }

  @Test
  public void givenRegisterURL_whenMockMVC_thenStatusOK_andReturnsWithRegister() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.post("/register"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().string("register"));
  }
}
