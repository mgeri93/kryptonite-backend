package com.greenfoxacademy.ferrilatakryptonitetribesapplication.Building;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class BuildingServiceTest {

  private BuildingService buildingService;

  @Mock private BuildingRepository buildingRepository;

  private Building academy = new Academy();

  @Test
  public void validBuilding_ReturnsTrue() {
    assertTrue(buildingService.isValidBuilding(academy));
  }

  @Test
  public void isValidBuildingWithIncorrectInput() {}

  @Test
  public void saveValidBuilding() {}
}
