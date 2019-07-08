package com.greenfoxacademy.ferrilatakryptonitetribesapplication.resource;

public interface ResourceService {

  boolean notNullKingdom(Resource resource);

  boolean amountSpecified(Resource resource);

  long timeDifference(Resource resource);

  void saveResource(Resource resource);
}
