package com.greenfoxacademy.ferrilatakryptonitetribesapplication.kingdom;

import com.greenfoxacademy.ferrilatakryptonitetribesapplication.applicationuser.ApplicationUserServiceImpl;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.exception.customexceptions.KingdomRelatedException;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.resource.ResourceServiceImpl;
import java.nio.charset.Charset;
import java.util.ArrayList;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser
public class KingdomControllerTest {

  private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
      MediaType.APPLICATION_JSON.getSubtype(),
      Charset.forName("utf8"));

  @Autowired
  MockMvc mockMvc;

  @MockBean
  ApplicationUserServiceImpl applicationUserService;

  @MockBean
  ResourceServiceImpl resourceService;

  @Test
  public void givenKingdomURL_whenMockMVC_thenStatusOK_andReturnsWithKingdom() throws Exception {
    mockMvc.perform(get("/kingdom/"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().string("kingdom"));
  }

  @Test
  public void getKingdomWithPathvariable() throws Exception {
    when(applicationUserService.getKingdomListByUserId(1))
        .thenReturn(new ArrayList<>());
    mockMvc.perform(get("/kingdom/1")
        .contentType(contentType)
        .content(""))
        .andDo(print())
        .andExpect(status().isOk());
  }

  @Test
  public void getKingdomWithNonExistentIdPathvariable() throws Exception {
    when(applicationUserService.getKingdomListByUserId(3))
        .thenThrow(new KingdomRelatedException("No user with this ID"));
    mockMvc.perform(get("/kingdom/3")
        .contentType(contentType)
        .content(""))
        .andDo(print())
        .andExpect(status().isBadRequest());
  }
}
