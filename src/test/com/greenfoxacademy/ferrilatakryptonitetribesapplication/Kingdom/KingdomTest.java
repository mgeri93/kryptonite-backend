package com.greenfoxacademy.ferrilatakryptonitetribesapplication.Kingdom;

import static org.junit.Assert.*;

import com.greenfoxacademy.ferrilatakryptonitetribesapplication.Kingdom.Kingdom;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.Kingdom.KingdomService;
import java.nio.charset.Charset;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class KingdomTest {

  @Autowired
  private KingdomService kingdomService;

  @Test
  public void isValidKingdomWithCorrectInputs () {
    assertTrue(kingdomService.isValidKingdom(new Kingdom("Attila", "Megye")));
  }

  @Test
  public void isValidKingdomWithNull() {
    assertFalse(kingdomService.isValidKingdom(new Kingdom(null, null)));
  }


}