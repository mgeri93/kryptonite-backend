package com.greenfoxacademy.ferrilatakryptonitetribesapplication.time;

import com.greenfoxacademy.ferrilatakryptonitetribesapplication.error.ErrorResponseServiceImpl;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.error.ErrorResponseModel;
import java.sql.Timestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TimeServiceImp implements TimeService {

  @Autowired
  ErrorResponseServiceImpl errorMessageService;

  @Override
  public Timestamp timeLeft(Timestamp start, Timestamp finish) throws ErrorResponseModel {
    if (finish.getTime() < start.getTime()) {
      throw errorMessageService.invalidTimeStamp("");
    }
    return new Timestamp(finish.getTime() - start.getTime());
  }
}
