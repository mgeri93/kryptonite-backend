package com.greenfoxacademy.ferrilatakryptonitetribesapplication.purchase;

import com.greenfoxacademy.ferrilatakryptonitetribesapplication.building.BuildingServiceImpl;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.kingdom.Kingdom;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.resource.Gold;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.resource.Resource;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.resource.ResourceServiceImpl;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.troop.TroopServiceImp;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.user.User;
import java.util.List;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PurchaseServiceImplTest {

  @InjectMocks
  private PurchaseServiceImpl purchaseService;

  @Mock
  private BuildingServiceImpl buildingService;

  @Mock
  private TroopServiceImp troopService;

  @Mock
  private ResourceServiceImpl resourceService;

  @Before
  public void init() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void canPurchaseTroop_Test() {
    Kingdom kingdom = new Kingdom("Stormwind", new User("Dani", "lel"));
    List<Resource> kingdomResource = kingdom.getResourceList();
    Gold gold = new Gold(100);
    kingdomResource.add(gold);
    assertEquals(90, purchaseService.purchaseTroop(kingdom));
  }

  @Test
  public void canPurchaseBuilding_Test() {
    Kingdom kingdom = new Kingdom("Stormwind", new User("Dani", "lel"));
    List<Resource> kingdomResource = kingdom.getResourceList();
    Gold gold = new Gold(100);
    kingdomResource.add(gold);
    assertEquals(0, purchaseService.purchaseBuilding(kingdom));
  }

  @Test
  public void canUpgradeTroopTest() {}

  @Test
  public void canUpgradeBuildingTest() {}
}
