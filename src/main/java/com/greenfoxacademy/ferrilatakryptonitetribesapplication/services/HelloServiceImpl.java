package com.greenfoxacademy.ferrilatakryptonitetribesapplication.services;

import org.springframework.stereotype.Service;

@Service
public class HelloServiceImpl implements HelloService {

  public String getHelloWorld() {
    return "Hello Wor0ld!";
  }
}
