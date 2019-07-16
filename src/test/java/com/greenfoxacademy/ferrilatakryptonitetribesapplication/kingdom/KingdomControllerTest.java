package com.greenfoxacademy.ferrilatakryptonitetribesapplication.kingdom;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class KingdomControllerTest {

  @Autowired
  MockMvc mockMvc;

  @Test
  public void givenKingdomURL_whenMockMVC_thenStatusOK_andReturnsWithKingdom() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/kingdom/"))
        .andDo(print())
        .andExpect(status().isForbidden())
        .andExpect(content().string(""));
  }
}
