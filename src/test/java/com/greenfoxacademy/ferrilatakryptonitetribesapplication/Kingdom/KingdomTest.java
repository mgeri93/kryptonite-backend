package com.greenfoxacademy.ferrilatakryptonitetribesapplication.Kingdom;

import static org.junit.Assert.*;

import com.greenfoxacademy.ferrilatakryptonitetribesapplication.Kingdom.Kingdom;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.Kingdom.KingdomService;
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

  @Test
  public void isValidKingdomWithValidInputs() {
    assertTrue (kingdomService.isValidKingdom(new Kingdom("Attila", "Megye")));
  }

  @Test
  public void isValidKingdomWithEmptyName() {
    assertFalse (kingdomService.isValidKingdom(new Kingdom("", "")));
  }


}