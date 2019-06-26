package com.greenfoxacademy.ferrilatakryptonitetribesapplication.resourcetest;

import static org.junit.Assert.*;

import com.greenfoxacademy.ferrilatakryptonitetribesapplication.kingdom.IKingdomRepository;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.resource.Gold;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.resource.IResourceRepository;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.resource.Resource;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.resource.ResourceFactory;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.resource.ResourceServiceImpl;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.resource.ResourceType;
import java.io.IOException;
import java.util.Optional;
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
  public void factoryWithValidEnum () {
    Resource myResource = ResourceFactory.createResource(ResourceType.Gold);
    assertTrue(myResource.getAmount() == 0);
  }
}