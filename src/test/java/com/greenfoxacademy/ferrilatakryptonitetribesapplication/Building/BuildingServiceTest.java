package com.greenfoxacademy.ferrilatakryptonitetribesapplication.Building;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class BuildingServiceTest {

  private BuildingService buildingService;

  @Mock private BuildingRepository buildingRepository;

  private Building academy = new Academy();

  private Building farm;

  @Before
  public void init() {
    MockitoAnnotations.initMocks(this);
    buildingService = new BuildingService(buildingRepository);
  }

  @Test
  public void validBuilding_ReturnsTrue() {
    assertTrue(buildingService.isValidBuilding(academy));
  }

  @Test
  public void isValidBuildingWithIncorrectInput() {
    assertFalse(buildingService.isValidBuilding(farm));
  }
}
