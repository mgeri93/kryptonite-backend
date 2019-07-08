package com.greenfoxacademy.ferrilatakryptonitetribesapplication.user;

import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ApplicationUserDTO {

  @NotNull
  private String username;
  @NotNull
  private String password;
  private String kingdom;

  public ApplicationUserDTO() {

  }

  public ApplicationUserDTO(String username, String password, String kingdom) {
    this.username = username;
    this.password = password;
    this.kingdom = kingdom;
  }
}
