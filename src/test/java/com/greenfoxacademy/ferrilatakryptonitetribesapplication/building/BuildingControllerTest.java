package com.greenfoxacademy.ferrilatakryptonitetribesapplication.building;

import com.greenfoxacademy.ferrilatakryptonitetribesapplication.kingdom.Kingdom;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.kingdom.KingdomServiceImpl;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.purchase.PurchaseServiceImpl;
import java.nio.charset.Charset;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser
public class BuildingControllerTest {

  private MediaType contentType =
      new MediaType(
          MediaType.APPLICATION_JSON.getType(),
          MediaType.APPLICATION_JSON.getSubtype(),
          Charset.forName("utf8"));

  @Autowired
  MockMvc mockMvc;

  @MockBean
  PurchaseServiceImpl purchaseService;

  @MockBean
  KingdomServiceImpl kingdomService;

  @Test
  public void buildingEndpointWithValidParams() throws Exception {
    when(kingdomService.findKingdomById(1)).thenReturn(new Kingdom());
    when(purchaseService.purchaseBuilding(
        kingdomService.findKingdomById(1), new BuildingDTO("Farm", 0, 1)))
        .thenReturn(0);
    mockMvc.perform(post("/building")
        .contentType(contentType)
        .content("{\"type\": \"Farm\", \"level\": \"0\", \"kingdomId\": \"1\"}"))
        .andDo(print())
        .andExpect(status().isOk());
  }
}
