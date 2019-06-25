package com.greenfoxacademy.ferrilatakryptonitetribesapplication.ResourceTest;

import static org.junit.Assert.*;

import com.greenfoxacademy.ferrilatakryptonitetribesapplication.Kingdom.IKingdomRepository;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.Kingdom.KingdomService;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.Kingdom.KingdomServiceImpl;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.Resource.Gold;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.Resource.Resource;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.Resource.ResourceServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ResourceServiceImplTest {

  @Autowired
  private ResourceServiceImpl resourceService;

  @Autowired
  private IKingdomRepository iKingdomRepository;

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