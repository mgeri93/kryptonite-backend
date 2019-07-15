package com.greenfoxacademy.ferrilatakryptonitetribesapplication.kingdom;

import com.greenfoxacademy.ferrilatakryptonitetribesapplication.exception.customexceptions.KingdomRelatedException;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.troop.Troop;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.troop.TroopServiceImp;
import java.nio.charset.Charset;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
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

  private MediaType contentType =
      new MediaType(
          MediaType.APPLICATION_JSON.getType(),
          MediaType.APPLICATION_JSON.getSubtype(),
          Charset.forName("utf8"));

  @Autowired
  MockMvc mockMvc;

  @MockBean
  KingdomServiceImpl kingdomService;

  @MockBean
  TroopServiceImp troopService;

  @Test
  public void givenKingdomURL_whenMockMVC_thenStatusOK_andReturnsWithKingdom() throws Exception {
    mockMvc
        .perform(get("/kingdom"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().string("kingdom"));
  }

  @Test
  public void troopsWithKingdomIdReturnsTroopsOfKingdom() throws Exception {
    Kingdom kingdom = new Kingdom();
    Troop troop = new Troop();
    Mockito.when(troopService.createTroop(kingdom)).thenReturn(troop);
    Mockito.when(kingdomService.findKingdomById((long) 0)).thenReturn(kingdom);
    mockMvc
        .perform(get("/kingdom/troops/0").contentType(contentType).content(""))
        .andDo(print())
        .andExpect(status().isOk());
  }

  @Test
  public void troopsWithKingdomNonExistentIdReturnException() throws Exception {
    Kingdom kingdom = new Kingdom();
    Troop troop = new Troop();
    Mockito.when(troopService.createTroop(kingdom)).thenReturn(troop);
    Mockito.when(kingdomService.findKingdomById((long) 1))
        .thenThrow((new KingdomRelatedException("Kingdom ID not found: " + 1)));
    mockMvc
        .perform(get("/kingdom/troops/1").contentType(contentType).content(""))
        .andDo(print())
        .andExpect(status().isBadRequest());
  }

  @Test
  public void troopsWithKingdomWithoutTroops() throws Exception {
    Kingdom kingdom = new Kingdom();
    kingdom.setId(0);
    Mockito.when(kingdomService.findKingdomById((long) 0))
        .thenThrow((new KingdomRelatedException("There is no troops in this kingdom")));
    mockMvc
        .perform(get("/kingdom/troops/0").contentType(contentType).content(""))
        .andDo(print())
        .andExpect(status().isBadRequest());
  }
}
