package com.greenfoxacademy.ferrilatakryptonitetribesapplication.troop;

import com.greenfoxacademy.ferrilatakryptonitetribesapplication.applicationuser.ApplicationUser;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.kingdom.Kingdom;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.resource.Food;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.resource.Gold;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TroopTest {

  @InjectMocks
  private TroopServiceImp troopServiceImp;

  @Before
  public void init() {
    MockitoAnnotations.initMocks(this);
  }


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
    Kingdom kingdom = new Kingdom("empire", new ApplicationUser("geri", "password"));
    testTroop.setKingdom(kingdom);
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

  @Test
  public void createTroopDecreaseFood() {
    Kingdom kingdom = new Kingdom("empire", new ApplicationUser("geri", "password"));
    kingdom.getResourceList().add(0, new Gold());
    kingdom.getResourceList().add(1, new Food(20));
    troopServiceImp.createTroop(kingdom);
    assertEquals(19, kingdom.getResourceList().get(1).getAmount());
  }
}
