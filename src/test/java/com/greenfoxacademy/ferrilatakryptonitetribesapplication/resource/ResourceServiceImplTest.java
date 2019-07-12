package com.greenfoxacademy.ferrilatakryptonitetribesapplication.resource;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.greenfoxacademy.ferrilatakryptonitetribesapplication.time.TimeServiceImp;
import java.sql.Timestamp;
import java.util.concurrent.TimeUnit;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ResourceServiceImplTest {

  @InjectMocks
  private ResourceServiceImpl resourceService;

  @Mock
  private TimeServiceImp timeService;

  @Mock
  private IResourceRepository resourceRepository;

  @Mock
  private Resource resource;

  @Before
  public void init() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void notNullKingdomWithNullKingdom() {
    Gold myGold = new Gold(4, null);
    assertFalse(resourceService.notNullKingdom(myGold));
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

  @Test
  public void refreshTest() throws Exception {
    Gold gold = new Gold(100);
    gold.setUpdatedAt(new Timestamp(System.currentTimeMillis() - 600000L));
    resourceService.refresh(gold);
    assertEquals(System.currentTimeMillis()/1000, gold.getUpdatedAt().getTime()/1000);
  }

}
