package com.greenfoxacademy.ferrilatakryptonitetribesapplication.purchase;

import static org.junit.Assert.assertEquals;

import com.greenfoxacademy.ferrilatakryptonitetribesapplication.applicationuser.ApplicationUser;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.building.Academy;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.building.Building;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.building.BuildingDTO;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.building.BuildingRepository;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.building.Farm;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.building.Mine;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.building.TownHall;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.exception.customexceptions.BuildingRelatedException;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.exception.customexceptions.KingdomRelatedException;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.exception.customexceptions.ResourceRelatedException;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.kingdom.IKingdomRepository;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.kingdom.Kingdom;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.resource.Gold;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.resource.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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

  @Test(expected = KingdomRelatedException.class)
  public void purchaseBuildingWithInsufficientGold() {
    Kingdom myKingdom = new Kingdom();
    myKingdom.setResourceList(new ArrayList<>());
    purchaseService.constructNewBuilding(new BuildingDTO("Mine", 0, 1));
  }

  @Test(expected = KingdomRelatedException.class)
  public void purchaseBuildingHigherLevelThanTownhall() {
    List<Resource> testResources = new ArrayList<>();
    testResources.add(new Gold(1000));
    Kingdom myKingdom = new Kingdom();
    myKingdom.setResourceList(testResources);
    purchaseService.constructNewBuilding(new BuildingDTO("Mine", 6, 1));
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

  @Test(expected = BuildingRelatedException.class)
  public void upgradeBuildingByOneLevelReturnsCorrectBuildingRelatedException() {
    Kingdom myKingdom = new Kingdom("Kutyavilág", new ApplicationUser());
    Academy myAcademy = new Academy();
    Farm myFarm = new Farm();
    TownHall myTownhall = new TownHall();
    Mine myMine = new Mine();
    myAcademy.setKingdom(myKingdom);
    myMine.setKingdom(myKingdom);
    myFarm.setKingdom(myKingdom);
    myTownhall.setKingdom(myKingdom);
    List<Building> myBuildings = new ArrayList<>(Arrays.asList(myMine, myAcademy,
        myFarm, myTownhall));
    List<Resource> myResources = new ArrayList<>(Arrays.asList(new Gold(5)));
    myKingdom.setResourceList(myResources);
    myKingdom.setBuildings(myBuildings);
    buildingRepository.saveAll(myBuildings);
    myKingdom.setResourceList(myResources);
    kingdomRepository.save(myKingdom);
    purchaseService.upgradeBuildingByOneLevel(2);
  }
}

