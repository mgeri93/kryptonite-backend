package com.greenfoxacademy.ferrilatakryptonitetribesapplication.Building;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuildingService {

  private BuildingRepository buildingRepository;

  @Autowired
  public BuildingService(BuildingRepository buildingRepository) {
    this.buildingRepository = buildingRepository;
  }

  public boolean isValidBuilding(Building building) {
    return (building.getBuildingType() != null && !building.getBuildingType().equals(""));
  }

  public void saveBuilding(Building building) {
    buildingRepository.save(building);
  }

  public Building findById(long id) {
    return buildingRepository.findById(id);
  }
}
