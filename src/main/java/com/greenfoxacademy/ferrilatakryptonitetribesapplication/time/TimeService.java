package com.greenfoxacademy.ferrilatakryptonitetribesapplication.time;

import java.sql.Timestamp;

interface TimeService {

  Long timeDifference(Timestamp start, Timestamp finish) throws Exception;

  long getCurrentTime();

  Timestamp calculateCompletionTimeOfTroop(long troopId);

  Timestamp calculateCompletionTimeOfBuilding(long buildingId);
}
