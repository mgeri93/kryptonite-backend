package com.greenfoxacademy.ferrilatakryptonitetribesapplication.kingdom;

import com.greenfoxacademy.ferrilatakryptonitetribesapplication.user.ApplicationUser;
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
  private KingdomServiceImpl kingdomService;


  @Test
  public void isValidKingdomWithIncorrectInputs() {
    assertFalse(kingdomService.isValidKingdom(new Kingdom("", new ApplicationUser())));
  }

  @Test
  public void isValidKingdomWithNull() {
    assertFalse(kingdomService.isValidKingdom(new Kingdom(null, new ApplicationUser())));
  }

  @Test
  public void isExistingKingdomForExisting() {
    Kingdom myKingdom = new Kingdom("Tanya", new ApplicationUser());
    assertFalse(kingdomService.isExistingKingdom(myKingdom));
  }
}
