package com.greenfoxacademy.ferrilatakryptonitetribesapplication.building;

public enum BuildingType {
  Academy {
    public Building makeBuilding() {
      return new Academy();
    }
  },

  Farm {
    public Building makeBuilding() {
      return new Farm();
    }
  },

  Mine {
    public Building makeBuilding() {
      return new Mine();
    }
  },

  TownHall {
    public Building makeBuilding() {
      return new TownHall();
    }
  }
}
