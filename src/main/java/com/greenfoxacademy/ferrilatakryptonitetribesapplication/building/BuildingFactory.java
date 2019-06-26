package com.greenfoxacademy.ferrilatakryptonitetribesapplication.building;

public class BuildingFactory {

  public static Building createBuilding(String buildingType) {
    if (buildingType == null) {
      return null;
    }
    if (buildingType.equalsIgnoreCase("FARM")) {
      return new Farm();
    } else if (buildingType.equalsIgnoreCase("ACADEMY")) {
      return new Academy();
    } else if (buildingType.equalsIgnoreCase("MINE")) {
      return new Mine();
    } else if (buildingType.equalsIgnoreCase("TOWNHALL")) {
      return new TownHall();
    }
    return null;
  }
}
