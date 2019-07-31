package com.greenfoxacademy.ferrilatakryptonitetribesapplication.kingdom;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.greenfoxacademy.ferrilatakryptonitetribesapplication.applicationuser.ApplicationUser;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.building.Academy;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.building.Building;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.building.Farm;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.building.Mine;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.building.TownHall;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.exception.customexceptions.KingdomRelatedException;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.resource.Food;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.resource.Gold;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.resource.Resource;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.resource.ResourceServiceImpl;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.troop.Troop;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.troop.TroopServiceImpl;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class KingdomServiceImplTest {

  @MockBean
  KingdomServiceImpl kingdomService;

  @MockBean
  ResourceServiceImpl resourceService;

  @MockBean
  TroopServiceImpl troopService;

  @MockBean
  IKingdomRepository kingdomRepository;

  @Autowired
  MockMvc mockMvc;


  @BeforeEach
  void init() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  void listKingdomsResources() {
    ApplicationUser appUser = new ApplicationUser();
    Kingdom kingdom = new Kingdom("Kingdom", appUser);
    List<Resource> resourceList = new ArrayList<>();
    resourceList.add(new Food());
    resourceList.add(new Gold());
    when(kingdomService.listKingdomsResources(kingdom.getId())).thenReturn(resourceList);
    assertEquals(2, kingdomService.listKingdomsResources(kingdom.getId()).size());

  }

  @Test
  void listKingdomsResourcesWithoutId() {
    ApplicationUser appUser = new ApplicationUser();
    Kingdom kingdom = new Kingdom("Kingdom", appUser);
    when(!kingdomRepository.existsById(kingdom.getId()))
        .thenThrow(new KingdomRelatedException("No Kingdom exists with this id"));
    assertThrows(KingdomRelatedException.class, this::listKingdomsResourcesWithoutId);
  }

  @Test
  void getTroopsOfKingdomById() {
    ApplicationUser appUser = new ApplicationUser();
    Kingdom kingdom = new Kingdom("Kingdom", appUser);
    List<Troop> troops = new ArrayList<>();
    troops.add(new Troop());
    troops.add(new Troop());
    when(kingdomService.getTroopsOfKingdomById(kingdom.getId())).thenReturn(troops);
    assertEquals(2, kingdomService.getTroopsOfKingdomById(kingdom.getId()).size());
  }

  @Test
  void listTroopsWithoutId() {
    ApplicationUser appUser = new ApplicationUser();
    Kingdom kingdom = new Kingdom("Kingdom", appUser);
    when(kingdomService.getTroopsOfKingdomById(kingdom.getId()))
        .thenThrow(new KingdomRelatedException("There are no troops in this kingdom"));
    assertThrows(KingdomRelatedException.class, this::listTroopsWithoutId);
  }

  @Test
  void getBuildingsOfKingdom() {
    ApplicationUser appUser = new ApplicationUser();
    Kingdom kingdom = new Kingdom("Kingdom", appUser);
    List<Building> buildings = new ArrayList<>();
    buildings.add(new Academy());
    buildings.add(new Farm());
    buildings.add(new Mine());
    buildings.add(new TownHall());
    when(kingdomService.getBuildingsOfKingdom(kingdom.getId())).thenReturn(
        ResponseEntity.status(200).body(kingdom.getBuildings()));
    assertEquals(ResponseEntity.status(200).body(kingdom.getBuildings()),
        kingdomService.getBuildingsOfKingdom(kingdom.getId()));
  }

  @Test
  void getBuildingWithoutBuildings() {
    ApplicationUser appUser = new ApplicationUser();
    Kingdom kingdom = new Kingdom("Kingdom", appUser);
    when(kingdomService.getBuildingsOfKingdom(kingdom.getId()))
        .thenThrow(new KingdomRelatedException("Oops, this kingdom has no buildings. What have you done?"));
    assertThrows(KingdomRelatedException.class, this::getBuildingWithoutBuildings);
  }
}
