package com.greenfoxacademy.ferrilatakryptonitetribesapplication.building;

import com.greenfoxacademy.ferrilatakryptonitetribesapplication.kingdom.KingdomServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuildingServiceImpl implements BuildingService {

  private BuildingRepository buildingRepository;
  private KingdomServiceImpl kingdomService;

  @Autowired
  public BuildingServiceImpl(BuildingRepository buildingRepository,
      KingdomServiceImpl kingdomService) {
    this.buildingRepository = buildingRepository;
    this.kingdomService = kingdomService;
  }

  @Override
  public boolean isValidBuilding(Building building) {
    return (building != null);
  }

  @Override
  public Building saveBuilding(Building building) {
    return buildingRepository.save(building);
  }

  @Override
  public Building findBuildingById(long id) {
    return buildingRepository.findBuildingById(id);
  }

}
