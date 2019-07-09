package com.greenfoxacademy.ferrilatakryptonitetribesapplication.time;

import com.greenfoxacademy.ferrilatakryptonitetribesapplication.exception.TimeRelatedException;
import java.sql.Timestamp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TimeServiceImpTest {

  @Autowired
  TimeServiceImp timeServiceImp;

  @Test
  public void timeLeftWithFutureFinish() {
    Timestamp testStamp = Timestamp.valueOf("1970-01-01 01:08:57.975");
    try {
      Timestamp myStamp = timeServiceImp.timeLeft(Timestamp.valueOf("2019-07-02 18:48:05.123"),
          Timestamp.valueOf("2019-07-02 18:57:03.098"));
      assertEquals(myStamp, testStamp);
    } catch (Exception e) {
      System.out.println("Invalid input parameters");
    }
  }

  @Test
  public void timeLeftWithAssertNotSame() {
    Timestamp testStamp = Timestamp.valueOf("1970-01-01 01:08:57.975");
    try {
      Timestamp myStamp = timeServiceImp.timeLeft(Timestamp.valueOf("2019-07-02 18:48:05.123"),
          Timestamp.valueOf("2019-07-02 18:51:03.098"));
      assertNotSame(myStamp, testStamp);
    } catch (Exception e) {
      System.out.println("Invalid input parameters");
    }
  }

  @Test
  public void timeLeftWithFinishEarlierThanStart() {
    boolean thrown = false;
    String testMessage = "Start time is later than finish time!";
    try {
      timeServiceImp.timeLeft(Timestamp.valueOf("2019-07-02 18:51:03.098"),
          Timestamp.valueOf("2018-07-02 18:51:03.098"));
    } catch (TimeRelatedException e) {
      thrown = true;
      assertEquals(e.getMessage(), testMessage);
    }
    assertTrue(thrown);
  }
}
