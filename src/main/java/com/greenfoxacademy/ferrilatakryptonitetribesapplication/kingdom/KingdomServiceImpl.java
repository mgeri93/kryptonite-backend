package com.greenfoxacademy.ferrilatakryptonitetribesapplication.kingdom;

import com.greenfoxacademy.ferrilatakryptonitetribesapplication.resource.ResourceServiceImpl;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.time.TimeServiceImp;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KingdomServiceImpl implements KingdomService {

  private IKingdomRepository kingdomRepository;
  private ResourceServiceImpl resourceService;
  private TimeServiceImp timeService;

  @Autowired
  public KingdomServiceImpl(
      IKingdomRepository kingdomRepository, TimeServiceImp timeService) {
    this.kingdomRepository = kingdomRepository;
    this.timeService = timeService;
  }

  public void setResourceService(ResourceServiceImpl resourceService) {
    this.resourceService = resourceService;
  }

  @Override
  public boolean isExistingKingdom(Kingdom kingdom) {
    return kingdomRepository.existsByName(kingdom.getName());
  }

  @Override
  public boolean isValidKingdom(Kingdom kingdom) {
    return (kingdom.getName() != null && !kingdom.getName().equals(""));
  }

  @Override
  public void updateResource(Kingdom kingdom){
    if ((resourceService.timeDifference(kingdom.getResourceList().get(0)) /60000) / /*UpgradeTime*/60 < 1 ) {
      kingdom.getResourceList().get(0).setAmount(kingdom.getResourceList().get(0).getAmount()
          - (int) (
          ((int) ((resourceService.timeDifference(kingdom.getResourceList().get(0)) / 60000)
              / /*Upgradetime:*/60)) * /*UpgradeCost:*/10));
    } else if ((resourceService.timeDifference(kingdom.getResourceList().get(0)) /60000) / /*UpgradeTime*/60 == 1) {
      kingdom.getResourceList().get(0).setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
    }
    kingdomRepository.save(kingdom);
  }
}
