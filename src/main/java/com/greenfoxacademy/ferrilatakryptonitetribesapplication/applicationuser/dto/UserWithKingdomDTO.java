package com.greenfoxacademy.ferrilatakryptonitetribesapplication.applicationuser.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class UserWithKingdomDTO {

  private long id;
  private String username;
  private long kingdomId;

  public UserWithKingdomDTO() {}

  public UserWithKingdomDTO(long id, String username, long kingdomId) {
    this.id = id;
    this.username = username;
    this.kingdomId = kingdomId;
  }
}
