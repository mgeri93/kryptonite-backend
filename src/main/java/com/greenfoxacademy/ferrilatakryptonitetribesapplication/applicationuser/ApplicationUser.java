package com.greenfoxacademy.ferrilatakryptonitetribesapplication.applicationuser;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ApplicationUser {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  private String username;
  private String password;

  public ApplicationUser() {

  }

  public ApplicationUser(String username, String password) {
    this.username = username;
    this.password = password;
  }
}
