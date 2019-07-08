package com.greenfoxacademy.ferrilatakryptonitetribesapplication.resource;

import java.sql.Timestamp;

public interface ResourceService {

  boolean notNullKingdom(Resource resource);

  boolean amountSpecified(Resource resource);

  long timeDifference(Resource resource);

  void saveResource(Resource resource);
}
