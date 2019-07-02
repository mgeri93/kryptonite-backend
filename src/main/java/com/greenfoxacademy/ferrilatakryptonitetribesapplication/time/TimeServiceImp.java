package com.greenfoxacademy.ferrilatakryptonitetribesapplication.time;

import java.sql.Timestamp;
import org.springframework.stereotype.Service;

@Service
public class TimeServiceImp implements TimeService {

  @Override
  public Timestamp timeLeft(Timestamp start, Timestamp finish) {
    long ts1 = start.getTime();
    long ts2 = finish.getTime() - 3600000L;
    return new Timestamp(ts2 - ts1);
  }
}
