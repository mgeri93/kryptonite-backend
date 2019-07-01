package com.greenfoxacademy.ferrilatakryptonitetribesapplication.time;

import java.sql.Timestamp;

public interface TimeService {
  public Timestamp timeLeft (Timestamp start, Timestamp finish);
}
