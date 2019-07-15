package com.greenfoxacademy.ferrilatakryptonitetribesapplication.kingdom;

import com.greenfoxacademy.ferrilatakryptonitetribesapplication.resource.Resource;
import java.util.List;

public interface KingdomService {

  boolean isExistingKingdom(Kingdom kingdom);

  boolean isValidKingdom(Kingdom kingdom);

  List<Resource> listKingdomsResources(long id);
}
