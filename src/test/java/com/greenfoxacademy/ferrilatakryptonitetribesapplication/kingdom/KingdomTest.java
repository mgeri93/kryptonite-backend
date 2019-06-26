package com.greenfoxacademy.ferrilatakryptonitetribesapplication.kingdom;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class KingdomTest {

  @Autowired
  private KingdomService kingdomService;

  @Autowired
  private IKingdomRepository kingdomRepository;

  @Test
  public void isValidKingdomWithCorrectInputs() {
    assertTrue(kingdomService.isValidKingdom(new Kingdom("Attila", "Megye")));
  }

  @Test
  public void isValidKingdomWithNull() {
    assertFalse(kingdomService.isValidKingdom(new Kingdom(null, null)));
  }

  @Test
  public void isExistingKingdomForExisting() {
    Kingdom myKingdom = new Kingdom("Attila", "Tanya");
    kingdomRepository.save(myKingdom);
    assertTrue(kingdomService.isExistingKingdom(myKingdom));
  }
}
