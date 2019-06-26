package com.greenfoxacademy.ferrilatakryptonitetribesapplication.kingdom;

import static org.junit.Assert.assertFalse;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class KingdomTest {


  @MockBean
  private KingdomService kingdomService;

  @MockBean
  private IKingdomRepository kingdomRepository;

  @Test
  public void isValidKingdomWithCorrectInputs() {
    assertFalse(kingdomService.isValidKingdom(new Kingdom("Attila", "Megye")));
  }

  @Test
  public void isValidKingdomWithNull() {
    assertFalse(kingdomService.isValidKingdom(new Kingdom(null, null)));
  }

  @Test
  public void isExistingKingdomForExisting() {
    Kingdom myKingdom = new Kingdom("Attila", "Tanya");
    kingdomRepository.save(myKingdom);
    assertFalse(kingdomService.isExistingKingdom(myKingdom));
  }
}
