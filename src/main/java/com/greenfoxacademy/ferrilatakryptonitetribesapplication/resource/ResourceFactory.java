package com.greenfoxacademy.ferrilatakryptonitetribesapplication.resource;

public class ResourceFactory {

  public static Resource createResource(ResourceType resourceType) {
    if (resourceType == null) {
      return null;
    }
    if (resourceType.equals(ResourceType.Gold)) {
      return new Gold();
    } else if (resourceType.equals(ResourceType.Food)) {
      return new Food();
    }
    return null;
  }
}
