package com.greenfoxacademy.ferrilatakryptonitetribesapplication.user;

import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

  @NotNull
  private String username;

  @NotNull
  private String password;
}
