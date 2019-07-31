package com.greenfoxacademy.ferrilatakryptonitetribesapplication.purchase;

import com.greenfoxacademy.ferrilatakryptonitetribesapplication.building.Academy;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.building.Building;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.building.Farm;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.building.Mine;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.building.TownHall;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.exception.customexceptions.BuildingRelatedException;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.exception.customexceptions.ResourceRelatedException;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.kingdom.Kingdom;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.resource.Gold;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.resource.Resource;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class PurchaseServiceJUnit5Test {

  @Autowired
  PurchaseServiceImpl purchaseService;

  private Farm myFarm = new Farm();
  private Mine myMine = new Mine();
  private Academy myAcademy = new Academy();
  private TownHall myTownhall = new TownHall();
  private Gold myGold = new Gold(9);
  private Kingdom myKingdom = new Kingdom();

  @Test
  public void purchaseTroopWithoutResources() {
    List<Resource> insufficient = Arrays.asList(myGold);
    List<Building> hasAll = Arrays.asList(myFarm, myMine, myAcademy, myTownhall);
    myKingdom.setResourceList(insufficient);
    myKingdom.setBuildings(hasAll);
    myKingdom.setId(1);
    Assertions.assertThrows(ResourceRelatedException.class, () -> {
      purchaseService.purchaseTroop(myKingdom);
    });
  }

  @Test
  public void purchaseTroopWithoutAcademy() {
    myGold.setAmount(500);
    List<Resource> sufficient = Arrays.asList(myGold);
    List<Building> noTownHall = Arrays.asList(myFarm, myMine, myTownhall);
    myKingdom.setResourceList(sufficient);
    myKingdom.setBuildings(noTownHall);
    myKingdom.setId(1);
    Assertions.assertThrows(BuildingRelatedException.class, () -> {
      purchaseService.purchaseTroop(myKingdom);
    });
  }
}