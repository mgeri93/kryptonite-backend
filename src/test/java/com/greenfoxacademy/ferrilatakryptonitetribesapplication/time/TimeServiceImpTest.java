package com.greenfoxacademy.ferrilatakryptonitetribesapplication.time;

import com.greenfoxacademy.ferrilatakryptonitetribesapplication.building.Building;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.building.BuildingServiceImpl;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.building.Farm;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.troop.Troop;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.troop.TroopServiceImp;
import java.sql.Timestamp;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TimeServiceImpTest {

  @MockBean
  TimeServiceImp timeServiceImp;

  @Mock
  TroopServiceImp troopService;

  @Mock
  BuildingServiceImpl buildingService;

  @Before
  public void init() {
    timeServiceImp = new TimeServiceImp(troopService, buildingService);
  }

  @Test
  public void timeLeftWithFutureFinish() {
    Timestamp testStamp = Timestamp.valueOf("1970-01-01 01:08:57.975");
    Long test = TimeUnit.MILLISECONDS.toMinutes(testStamp.getTime());
    try {
      Long myStamp =
          timeServiceImp.timeDifference(
              Timestamp.valueOf("2019-07-02 18:48:05.123"),
              Timestamp.valueOf("2019-07-02 18:57:03.098"));
      assertEquals(myStamp, test);
    } catch (Exception e) {
      System.out.println("Invalid input parameters");
    }
  }

  @Test
  public void timeLeftWithAssertNotSame() {
    Timestamp testStamp = Timestamp.valueOf("1970-01-01 01:08:57.975");
    try {
      Timestamp myStamp = new Timestamp(
          timeServiceImp.timeDifference(
              Timestamp.valueOf("2019-07-02 18:48:05.123"),
              Timestamp.valueOf("2019-07-02 18:51:03.098")));
      assertNotSame(myStamp, testStamp);
    } catch (Exception e) {
      System.out.println("Invalid input parameters");
    }
  }

  @Test
  public void timeLeftWithFinishEarlierThanStart() {
    boolean thrown = false;
    try {
      timeServiceImp.timeDifference(
          Timestamp.valueOf("2019-07-02 18:51:03.098"),
          Timestamp.valueOf("2018-07-02 18:51:03.098"));
    } catch (Exception e) {
      thrown = true;
    }
    assertTrue(thrown);
  }

  @Test
  public void calculatesCorrectTroopUpgradeTime() {
    Troop troop = new Troop();
    troop.setLevel(3);
    Mockito.when(troopService.findTroopById(1)).thenReturn(troop);
    assertEquals(
        new Timestamp(timeServiceImp.getCurrentTime() + TimeUnit.MINUTES.toMillis(3)),
        timeServiceImp.calculateCompletionTimeOfTroop(1));
  }

  @Test
  public void calculatesCorrectBuildingUpgradeTime() {
    Building farm = new Farm();
    farm.setLevel(10);
    Mockito.when(buildingService.findBuildingById(1)).thenReturn(farm);
    assertEquals(
        new Timestamp(timeServiceImp.getCurrentTime() + TimeUnit.MINUTES.toMillis(50)).getTime()
            / 1000,
        timeServiceImp.calculateCompletionTimeOfBuilding(1).getTime() / 1000);
  }
}
