package com.greenfoxacademy.ferrilatakryptonitetribesapplication.user;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  private String username;
  private String password;

  public User(){
  }

  public User(String username, String password) {
    this.username = username;
    this.password = password;
  }


}
