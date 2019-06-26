package com.greenfoxacademy.ferrilatakryptonitetribesapplication.resource;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
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

  @Test
  public void factoryWithValidEnum() {
    Resource myResource = ResourceFactory.createResource(ResourceType.Gold);
    assertTrue(myResource.getAmount() == 0);
  }
}