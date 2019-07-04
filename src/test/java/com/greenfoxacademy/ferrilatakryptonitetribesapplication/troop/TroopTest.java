package com.greenfoxacademy.ferrilatakryptonitetribesapplication.troop;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.Mockito.when;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TroopTest {

  @MockBean
  private TroopServiceImp troopServiceImp;

  @Test
  public void constructorWithAllInvalidParameters() {
    Troop anotherTroop = new Troop(-1, -4, 0, 0);
    assertEquals(anotherTroop.getLevel(), 1);
    assertEquals(anotherTroop.getHp(), 1);
    assertEquals(anotherTroop.getAttack(), 1);
    assertEquals(anotherTroop.getDefense(), 1);
  }

  @Test
  public void troopValidityCheck() {
    Troop testTroop = new Troop(-1, -4, 0, 0);
    when(troopServiceImp.isValidTroop(testTroop)).thenReturn(true);
    assertTrue(troopServiceImp.isValidTroop(testTroop));
  }

  @Test
  public void noParameterConstructor() {
    Troop testTroop = new Troop(1, 1, 1, 1);
    Troop defaultTroop = new Troop();
    assertEquals(testTroop.getLevel(), defaultTroop.getLevel());
    assertEquals(testTroop.getHp(), defaultTroop.getHp());
    assertEquals(testTroop.getAttack(), defaultTroop.getAttack());
    assertEquals(testTroop.getDefense(), defaultTroop.getDefense());
  }
}
