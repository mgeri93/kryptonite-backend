package com.greenfoxacademy.ferrilatakryptonitetribesapplication.purchase;

import com.greenfoxacademy.ferrilatakryptonitetribesapplication.applicationuser.ApplicationUser;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.building.Academy;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.building.Building;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.building.BuildingDTO;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.building.BuildingRepository;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.exception.customexceptions.BuildingRelatedException;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.exception.customexceptions.KingdomRelatedException;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.kingdom.IKingdomRepository;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.kingdom.Kingdom;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.resource.Gold;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.resource.Resource;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.troop.TroopServiceImpl;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PurchaseServiceUnitTest {

  @Autowired
  private PurchaseService purchaseService;

  @Autowired
  private BuildingRepository buildingRepository;

  @Autowired
  private IKingdomRepository kingdomRepository;

  @Autowired
  private TroopServiceImpl troopServiceImpl;

  @Test(expected = KingdomRelatedException.class)
  public void purchaseBuildingWithInsufficientGold() {
    Kingdom myKingdom = new Kingdom();
    myKingdom.setResourceList(new ArrayList<>());
    purchaseService.constructNewBuilding(new BuildingDTO("Mine", 0, 0));
  }

  @Test(expected = KingdomRelatedException.class)
  public void purchaseBuildingHigherLevelThanTownhall() {
    List<Resource> testResources = new ArrayList<>();
    testResources.add(new Gold(1000));
    Kingdom myKingdom = new Kingdom();
    myKingdom.setResourceList(testResources);
    purchaseService.constructNewBuilding(new BuildingDTO("Mine", 6, 0));
  }

  @Test(expected = BuildingRelatedException.class)
  public void purchaseBuildingWithInvalidType() {
    Kingdom myKingdom = new Kingdom();
    myKingdom.setResourceList(new ArrayList<>());
    purchaseService.constructNewBuilding(new BuildingDTO("Hospital", 0, 1));
  }

  @Test(expected = KingdomRelatedException.class)
  public void purchaseBuildingForNonExistentKingdom() {
    Kingdom myKingdom = new Kingdom();
    myKingdom.setResourceList(new ArrayList<>());
    purchaseService.constructNewBuilding(new BuildingDTO("Mine", 0, 5));
  }

  @Test(expected = BuildingRelatedException.class)
  public void purchaseBuildingWithEmptyBody() {
    BuildingDTO testDTO = null;
    Kingdom myKingdom = new Kingdom();
    myKingdom.setResourceList(new ArrayList<>());
    purchaseService.constructNewBuilding(testDTO);
  }

  @Test(expected = BuildingRelatedException.class)
  public void purchaseBuildingForTownhall() {
    purchaseService.constructNewBuilding(new BuildingDTO("Townhall", 0, 1));
  }

  @Test
  public void executeBuildingReducesGoldAmount() {
    Kingdom myKingdom = new Kingdom("Kutyavilág", new ApplicationUser());
    List<Building> myBuildings = new ArrayList<>();
    Academy academy = new Academy();
    academy.setKingdom(myKingdom);
    myBuildings.add(academy);
    List<Resource> myResources = new ArrayList<>();
    myResources.add(new Gold(200));
    myKingdom.setBuildings(myBuildings);
    buildingRepository.save(myKingdom.getBuildings().get(0));
    myKingdom.setResourceList(myResources);
    purchaseService.executeBuildingUpgrade(myKingdom.getBuildings().get(0), 1,
        myResources, myKingdom);
    assertEquals(myKingdom.getResourceList().get(0).getAmount(), 175);
  }
}

