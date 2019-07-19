package com.greenfoxacademy.ferrilatakryptonitetribesapplication.building;

import com.greenfoxacademy.ferrilatakryptonitetribesapplication.kingdom.KingdomServiceImpl;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class BuildingServiceImplTest {

  @MockBean
  private BuildingServiceImpl buildingService;

  @Mock
  private BuildingRepository buildingRepository;

  @Mock
  private KingdomServiceImpl kingdomService;

  @Before
  public void init() {
    MockitoAnnotations.initMocks(this);
    buildingService = new BuildingServiceImpl(buildingRepository, kingdomService);
  }

  @Test
  public void isValidBuildingWithCorrectInput() {
    Building academy = BuildingFactory.createBuilding(BuildingType.Academy);
    assertTrue(buildingService.isValidBuilding(academy));
  }

  @Test
  public void isValidBuildingWithIncorrectInput() {
    Building farm = null;
    assertFalse(buildingService.isValidBuilding(farm));
  }

  @Test
  public void saveValidBuilding() {
    Building mine = BuildingFactory.createBuilding(BuildingType.Mine);
    when(buildingService.saveBuilding(any(Building.class))).thenReturn(mine);
    assertEquals(mine, buildingRepository.save(mine));
  }

  @Test
  public void findValidBuildingById() {
    Building buildingToReturn = BuildingFactory.createBuilding(BuildingType.Academy);
    buildingToReturn.setLevel(100L);
    when(buildingService.findBuildingById(1)).thenReturn(buildingToReturn);
    assertEquals(100L, buildingRepository.findBuildingById(1).getLevel(), 0);
  }
}
