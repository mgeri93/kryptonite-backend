package com.greenfoxacademy.ferrilatakryptonitetribesapplication.kingdom;

import com.greenfoxacademy.ferrilatakryptonitetribesapplication.resource.ResourceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KingdomServiceImpl implements KingdomService {

  private IKingdomRepository kingdomRepository;
  private ResourceServiceImpl resourceService;

  @Autowired
  public KingdomServiceImpl(
      IKingdomRepository kingdomRepository) {
    this.kingdomRepository = kingdomRepository;
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
    kingdom.getResourceList().get(0).setAmount(kingdom.getResourceList().get(0).getAmount()
        - (int)(((int)((resourceService.timeDifference(kingdom.getResourceList().get(0)) / 60000)
        / /*Updtae time:*/60 )) * /*UpgradeCost:*/10));
    kingdomRepository.save(kingdom);
  }
}
