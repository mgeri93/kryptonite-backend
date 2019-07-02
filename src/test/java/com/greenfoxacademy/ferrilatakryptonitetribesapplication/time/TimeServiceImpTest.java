package com.greenfoxacademy.ferrilatakryptonitetribesapplication.time;

import java.sql.Timestamp;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.Mockito.when;
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

  @Test
  public void timeLeftWithFutureFinish() {
    String answer = "1970-01-01 01:08:57.975";
    Timestamp testStamp = Timestamp.valueOf(answer);
    when(timeServiceImp.timeLeft(Timestamp.valueOf("2019-07-02 18:48:05.123"),
        Timestamp.valueOf("2019-07-02 18:57:03.098"))).thenReturn(testStamp);
    Timestamp myStamp = timeServiceImp.timeLeft(Timestamp.valueOf("2019-07-02 18:48:05.123"),
        Timestamp.valueOf("2019-07-02 18:57:03.098"));
    assertEquals(myStamp, testStamp);
  }
}
