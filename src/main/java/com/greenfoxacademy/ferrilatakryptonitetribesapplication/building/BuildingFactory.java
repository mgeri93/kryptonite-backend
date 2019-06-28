package com.greenfoxacademy.ferrilatakryptonitetribesapplication.building;

public class BuildingFactory {

  public static Building createBuilding(BuildingType buildingType) {
    if (buildingType == null) {
      return null;
    }
    if (buildingType.equals(BuildingType.Farm)) {
      return new Farm();
    } else if (buildingType.equals(BuildingType.Academy)) {
      return new Academy();
    } else if (buildingType.equals(BuildingType.Mine)) {
      return new Mine();
    } else if (buildingType.equals(BuildingType.TownHall)) {
      return new TownHall();
    }
    return null;
  }
}
