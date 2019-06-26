package com.greenfoxacademy.ferrilatakryptonitetribesapplication.resourcetest;

import static org.junit.Assert.*;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.resource.Gold;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.resource.ResourceServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ResourceServiceImplTest {

  @MockBean
  private ResourceServiceImpl resourceService;


  @Test
  public void notNullKingdomWithNullKingdom() {
    Gold myGold = new Gold(4, null);
    assertFalse (resourceService.notNullKingdom(myGold));
  }

  @Test
  public void amountSpecifiedWithZero() {
    Gold myGold = new Gold(0, null);
    assertFalse(resourceService.amountSpecified(myGold));
  }
}