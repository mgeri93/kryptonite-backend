package com.greenfoxacademy.ferrilatakryptonitetribesapplication.KingdomTests;

import static org.junit.Assert.*;

import com.greenfoxacademy.ferrilatakryptonitetribesapplication.Kingdom.IKingdomRepository;
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

  @Autowired
  private IKingdomRepository iKingdomRepository;

  @Test
  public void isValidKingdomWithCorrectInputs () {
    assertTrue(kingdomService.isValidKingdom(new Kingdom("Attila", "Megye")));
  }

  @Test
  public void isValidKingdomWithNull() {
    assertFalse(kingdomService.isValidKingdom(new Kingdom(null, null)));
  }

  @Test
  public void isExistingKingdomForExisting() {
    Kingdom myKingdom = new Kingdom("Attila", "Tanya");
    iKingdomRepository.save(myKingdom);
    assertTrue(kingdomService.isExistingKingdom(myKingdom));
  }
}
