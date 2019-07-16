package com.greenfoxacademy.ferrilatakryptonitetribesapplication.time;

import com.greenfoxacademy.ferrilatakryptonitetribesapplication.building.BuildingServiceImpl;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.exception.customexceptions.TimeRelatedException;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.troop.TroopServiceImp;
import java.sql.Timestamp;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TimeServiceImp implements TimeService {

  private TroopServiceImp troopService;

  private BuildingServiceImpl buildingService;

  @Autowired
  public TimeServiceImp(TroopServiceImp troopService, BuildingServiceImpl buildingService) {
    this.troopService = troopService;
    this.buildingService = buildingService;
  }

  @Override
  public Long timeDifference(Timestamp start, Timestamp finish) {
    if (finish.getTime() < start.getTime()) {
      throw new TimeRelatedException("Start time is later than finish time!");
    }
    long diff = finish.getTime() - start.getTime();
    return TimeUnit.MILLISECONDS.toMinutes(diff);
  }

  @Override
  public long getCurrentTime() {
    return new Timestamp(System.currentTimeMillis()).getTime();
  }

  @Override
  public Timestamp calculateCompletionTimeOfTroop(long troopId) {
    long completionTime = troopService.findTroopById(troopId).getLevel();
    return new Timestamp(getCurrentTime() + TimeUnit.MINUTES.toMillis(completionTime));
  }

  @Override
  public Timestamp calculateCompletionTimeOfBuilding(long buildingId) {
    long completionTime = buildingService.findBuildingById(buildingId).getLevel() * 5;
    return new Timestamp(getCurrentTime() + TimeUnit.MINUTES.toMillis(completionTime));
  }
}
