package com.greenfoxacademy.ferrilatakryptonitetribesapplication.purchase;

import com.greenfoxacademy.ferrilatakryptonitetribesapplication.applicationuser.ApplicationUser;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.applicationuser.ApplicationUserServiceImpl;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.building.Building;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.building.BuildingServiceImpl;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.building.BuildingType;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.building.Farm;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.building.TownHall;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.kingdom.Kingdom;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.resource.Gold;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.resource.Resource;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.resource.ResourceServiceImpl;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.troop.Troop;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.troop.TroopServiceImpl;

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
  private TroopServiceImpl troopService;

  @Mock
  private ApplicationUserServiceImpl userService;

  @Mock
  private ResourceServiceImpl resourceService;

  private Kingdom kingdom = new Kingdom("Stormwind", new ApplicationUser("Dani", "lel"));
  private List<Resource> kingdomResource = kingdom.getResourceList();
  private List<Building> buildings = kingdom.getBuildings();
  private Building townhHall = new TownHall();
  private Building farm = new Farm();
  private Gold gold = new Gold(200);

  @Before
  public void init() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void canPurchaseTroop_Test() throws Exception {
    kingdomResource.add(gold);
    assertEquals(190, purchaseService.purchaseTroop(kingdom));
  }

  @Test
  public void canUpgradeBuildingTest() throws Exception {
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
    kingdomResource.add(gold);
    Troop troop = new Troop();
    troop.setLevel(1);
    List<Troop> kingdomTroops = kingdom.getTroops();
    kingdomTroops.add(troop);
    when(troopService.findTroopById(1)).thenReturn(troop);
    assertEquals(170, purchaseService.purchaseTroopUpgrade(kingdom, (long) 1, (long) 3));
  }

  @Test
  public void cantUpgradeAboveTownHall() throws Exception {
    kingdomResource.add(gold);
    townhHall.setId(1);
    farm.setId(2);
    buildings.add(townhHall);
    buildings.add(farm);
    townhHall.setLevel(2);
    townhHall.setBuildingType(BuildingType.TownHall);
    farm.setLevel(2);
    farm.setBuildingType(BuildingType.Farm);
    when(buildingService.findBuildingById(2)).thenReturn(farm);
    purchaseService.purchaseBuildingUpgrade(kingdom, farm.getId(), 3L);
    assertEquals(2, farm.getLevel());
  }

  @Test
  public void upgradeBuildingProperly() throws Exception {
    kingdomResource.add(gold);
    townhHall.setId(1);
    farm.setId(2);
    buildings.add(townhHall);
    buildings.add(farm);
    townhHall.setLevel(5);
    townhHall.setBuildingType(BuildingType.TownHall);
    farm.setLevel(2);
    farm.setBuildingType(BuildingType.Farm);
    when(buildingService.findBuildingById(2)).thenReturn(farm);
    purchaseService.purchaseBuildingUpgrade(kingdom, farm.getId(), 3L);
    assertEquals(3, farm.getLevel());
  }

  @Test
  public void cantUpgradeTroopAboveLevel3() throws Exception {
    kingdomResource.add(gold);
    Troop troop = new Troop();
    troop.setLevel(3);
    List<Troop> kingdomTroops = kingdom.getTroops();
    kingdomTroops.add(troop);
    when(troopService.findTroopById(1)).thenReturn(troop);
    purchaseService.purchaseTroopUpgrade(kingdom, 1L, 4L);
    assertEquals(3, troop.getLevel());
  }

  @Test
  public void upgradeTroopLevelProperly() throws Exception {
    kingdomResource.add(gold);
    Troop troop = new Troop();
    troop.setLevel(1);
    List<Troop> kingdomTroops = kingdom.getTroops();
    kingdomTroops.add(troop);
    when(troopService.findTroopById(1)).thenReturn(troop);
    purchaseService.purchaseTroopUpgrade(kingdom, 1L, 2L);
    assertEquals(2, troop.getLevel());
  }
}
