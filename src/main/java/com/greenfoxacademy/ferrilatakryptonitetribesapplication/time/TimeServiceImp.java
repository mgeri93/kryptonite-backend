package com.greenfoxacademy.ferrilatakryptonitetribesapplication.time;

import com.greenfoxacademy.ferrilatakryptonitetribesapplication.exception.TimeRelatedException;
import java.io.IOException;
import java.sql.Timestamp;
import org.springframework.stereotype.Service;

@Service
public class TimeServiceImp implements TimeService {

  @Override
  public Timestamp timeLeft(Timestamp start, Timestamp finish) {
    if (finish.getTime() < start.getTime()) {
      throw new TimeRelatedException("Start time is later than finish time!", "");
    }
    return new Timestamp(finish.getTime() - start.getTime());
  }
}
