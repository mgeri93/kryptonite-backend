package com.greenfoxacademy.ferrilatakryptonitetribesapplication.purchase;

import static org.junit.jupiter.api.Assertions.*;

import com.greenfoxacademy.ferrilatakryptonitetribesapplication.applicationuser.ApplicationUser;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.building.Academy;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.building.Building;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.building.BuildingRepository;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.building.Farm;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.building.Mine;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.building.TownHall;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.exception.customexceptions.BuildingRelatedException;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.exception.customexceptions.ResourceRelatedException;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.kingdom.IKingdomRepository;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.kingdom.Kingdom;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.resource.Gold;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.resource.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PurchaseServiceJUnit5Test {

  @Autowired
  private PurchaseService purchaseService;

  @Autowired
  private BuildingRepository buildingRepository;

  @Autowired
  private IKingdomRepository kingdomRepository;

  public void setup(TownHall myTownhall) {
    Kingdom myKingdom = new Kingdom("Kutyavilág", new ApplicationUser());
    Academy myAcademy = new Academy();
    Farm myFarm = new Farm();
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
  }

  @Test
  public void upgradeBuildingByOneLevelReturnsCorrectBuildingRelatedException() {
    setup(new TownHall());
    Assertions.assertThrows(BuildingRelatedException.class, () -> {
      purchaseService.upgradeBuildingByOneLevel(2);
    });
  }

  @Test
  public void upgradeBuildingByOneLevelReturnsCorrectResourceRelatedException() {
    TownHall level5Townhall = new TownHall();
    level5Townhall.setLevel(5);
    setup(level5Townhall);
    Assertions.assertThrows(ResourceRelatedException.class, () -> {
      purchaseService.upgradeBuildingByOneLevel(2);
    });

  }
}