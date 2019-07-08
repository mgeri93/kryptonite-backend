package com.greenfoxacademy.ferrilatakryptonitetribesapplication.purchase;

import com.greenfoxacademy.ferrilatakryptonitetribesapplication.building.Building;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.building.BuildingServiceImpl;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.building.Farm;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.kingdom.Kingdom;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.resource.Gold;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.resource.Resource;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.resource.ResourceServiceImpl;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.troop.Troop;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.troop.TroopServiceImp;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.user.ApplicationUser;
import java.util.List;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
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

  private Kingdom kingdom = new Kingdom("Stormwind", new ApplicationUser("Dani", "lel"));
  private List<Resource> kingdomResource = kingdom.getResourceList();

  @Before
  public void init() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void canPurchaseTroop_Test() throws Exception {
    Gold gold = new Gold(100);
    kingdomResource.add(gold);
    assertEquals(90, purchaseService.purchaseTroop(kingdom));
  }

  @Test
  public void canPurchaseBuilding_Test() throws Exception {
    Gold gold = new Gold(100);
    kingdomResource.add(gold);
    assertEquals(0, purchaseService.purchaseBuilding(kingdom));
  }

  @Test
  public void canUpgradeBuildingTest() throws Exception {
    Gold gold = new Gold(200);
    kingdomResource.add(gold);
    Building farm = new Farm();
    farm.setLevel(1);
    List<Building> kingdomBuildings = kingdom.getBuildings();
    kingdomBuildings.add(farm);
    when(buildingService.findBuildingById(1)).thenReturn(farm);
    assertEquals(0, purchaseService.purchaseBuildingUpgrade(kingdom, (long) 1, (long) 2));
  }

  @Test
  public void canUpgradeTroopTest() throws Exception {
    Gold gold = new Gold(200);
    kingdomResource.add(gold);
    Troop troop = new Troop();
    troop.setLevel(1);
    List<Troop> kingdomTroops = kingdom.getTroops();
    kingdomTroops.add(troop);
    when(troopService.findTroopById(1)).thenReturn(troop);
    assertEquals(170, purchaseService.purchaseTroopUpgrade(kingdom, (long) 1, (long) 3));
  }
}
