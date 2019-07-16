package com.greenfoxacademy.ferrilatakryptonitetribesapplication.kingdom;

import com.greenfoxacademy.ferrilatakryptonitetribesapplication.building.Academy;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.building.Building;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.exception.customexceptions.KingdomRelatedException;
import java.nio.charset.Charset;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

  @Test
  public void givenKingdomURL_whenMockMVC_thenStatusOK_andReturnsWithKingdom() throws Exception {
    mockMvc.perform(get("/kingdom"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().string("kingdom"));
  }

  @Test
  public void listBuildingsOfKingdom() throws Exception {
    Kingdom kingdom = new Kingdom();
    Building academy = new Academy();
    kingdom.getBuildings().add(academy);
    Mockito.when(kingdomService.getBuildingsOfKingdom(0))
        .thenReturn(ResponseEntity.status(200).body(kingdom.getBuildings()));
    mockMvc
        .perform(get("/kingdom/buildings/0").contentType(contentType).content(""))
        .andDo(print())
        .andExpect(status().isOk());
  }

  @Test
  public void listBuildingsNotValidId() throws Exception {
    Kingdom kingdom = new Kingdom();
    Building academy = new Academy();
    kingdom.getBuildings().add(academy);
    Mockito.when(kingdomService.getBuildingsOfKingdom(99))
        .thenThrow((new KingdomRelatedException("Kingdom ID not found:" + 99)));
    mockMvc
        .perform(get("/kingdom/buildings/99").contentType(contentType).content(""))
        .andDo(print())
        .andExpect(status().isBadRequest());
  }

  @Test
  public void buildingsOfKingdomWithNoBuildings() throws Exception {
    Kingdom kingdom =  new Kingdom();
    Mockito.when(kingdomService.getBuildingsOfKingdom(kingdom.getId()))
        .thenThrow((new KingdomRelatedException(
            "Oops, this kingdom has no buildings. What have you done?")));
    mockMvc
        .perform(get("/kingdom/buildings/0").contentType(contentType).content(""))
        .andDo(print())
        .andExpect(status().isBadRequest());
  }
}
