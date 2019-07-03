package com.greenfoxacademy.ferrilatakryptonitetribesapplication.time;

import java.sql.Timestamp;
import org.springframework.stereotype.Service;

@Service
public class TimeServiceImp implements TimeService {

  @Override
  public Timestamp timeLeft(Timestamp start, Timestamp finish) {
    if (finish.getTime() < start.getTime()) {
      return Timestamp.valueOf("1970-01-01 00:00:00.000");
    }
    return new Timestamp(finish.getTime() - start.getTime());
  }
}
