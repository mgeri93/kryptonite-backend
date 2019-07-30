package com.greenfoxacademy.ferrilatakryptonitetribesapplication.purchase;

import com.greenfoxacademy.ferrilatakryptonitetribesapplication.building.BuildingDTO;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.exception.customexceptions.BuildingRelatedException;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.exception.customexceptions.KingdomRelatedException;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.exception.customexceptions.ResourceRelatedException;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.kingdom.IKingdomRepository;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.kingdom.Kingdom;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.resource.Gold;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.resource.Resource;
import java.util.ArrayList;
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

  @Test(expected = ResourceRelatedException.class)
  public void purchaseTroopWithoutResources() throws Exception {
    Kingdom myKingdom = new Kingdom();
    List<Resource> insufficient = new ArrayList<>();
    insufficient.add(new Gold(5));
    myKingdom.setId(1);
    myKingdom.setResourceList(insufficient);
    purchaseService.purchaseTroop(myKingdom);
  }

  @Test(expected = BuildingRelatedException.class)
  public void purchaseTroopWithoutTownhall() throws Exception {
    Kingdom myKingdom = new Kingdom();
    List<Resource> sufficient = new ArrayList<>();
    myKingdom.setBuildings(new ArrayList<>());
    myKingdom.setResourceList(sufficient);
    sufficient.add(new Gold(10));
    purchaseService.purchaseTroop(myKingdom);
  }
}

