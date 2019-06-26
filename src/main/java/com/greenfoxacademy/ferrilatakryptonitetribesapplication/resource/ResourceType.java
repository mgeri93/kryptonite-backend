package com.greenfoxacademy.ferrilatakryptonitetribesapplication.resource;

public enum ResourceType {
  Food {
    public Resource makeResource () {
      return new Food();
    }
  },

  Gold {
    public Resource makeResource () {
      return new Gold();
    }
  }
}
