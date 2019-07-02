package com.greenfoxacademy.ferrilatakryptonitetribesapplication.user;

import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class UserDTO {

  @NotNull
  private String username;
  @NotNull
  private String password;
  private String kingdom;

  public UserDTO() {}

  public UserDTO(String username, String password, String kingdom) {
    this.username = username;
    this.password = password;
    this.kingdom = kingdom;
  }
}
