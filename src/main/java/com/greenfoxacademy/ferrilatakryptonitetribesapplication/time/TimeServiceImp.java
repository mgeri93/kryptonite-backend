package com.greenfoxacademy.ferrilatakryptonitetribesapplication.time;

import java.io.IOException;
import java.sql.Timestamp;
import org.springframework.stereotype.Service;

@Service
public class TimeServiceImp implements TimeService {

  @Override
  public Timestamp timeLeft(Timestamp start, Timestamp finish) throws Exception {
    if (finish.getTime() < start.getTime()) {
      throw new IOException();
    }
    return new Timestamp(finish.getTime() - start.getTime());
  }
}
