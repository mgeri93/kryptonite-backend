package com.greenfoxacademy.ferrilatakryptonitetribesapplication.time;

import java.sql.Timestamp;

interface TimeService {
  Timestamp timeLeft(Timestamp start, Timestamp finish) throws Exception;
}
