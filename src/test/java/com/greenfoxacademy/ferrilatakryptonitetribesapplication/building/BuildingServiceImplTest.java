package com.greenfoxacademy.ferrilatakryptonitetribesapplication.building;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class BuildingServiceImplTest {

  private BuildingServiceImpl buildingServiceImpl;

  @Mock private BuildingRepository buildingRepository;

  private Building academy = BuildingFactory.createBuilding("Academy");

  private Building farm;

  @Before
  public void init() {
    MockitoAnnotations.initMocks(this);
    buildingServiceImpl = new BuildingServiceImpl(buildingRepository);
  }

  @Test
  public void isValidBuildingWithCorrectInput() {
    assertTrue(buildingServiceImpl.isValidBuilding(academy));
  }

  @Test
  public void isValidBuildingWithIncorrectInput() {
    assertFalse(buildingServiceImpl.isValidBuilding(farm));
  }

  @Test
  public void saveValidBuilding() {
    Building mine = new Mine();
    when(buildingServiceImpl.saveBuilding(any(Building.class))).thenReturn(mine);
  }

  @Test
  public void findValidBuildingById() {
    Building buildingToReturn = new Academy();
    when(buildingServiceImpl.findById(1)).thenReturn(buildingToReturn);
  }
}
