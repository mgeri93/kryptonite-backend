package com.greenfoxacademy.ferrilatakryptonitetribesapplication.resource;

public interface ResourceService {

  boolean notNullKingdom(Resource resource);

  boolean amountSpecified(Resource resource);

  void saveResource(Resource resource);

  void refresh(Resource resource) throws Exception;

}
