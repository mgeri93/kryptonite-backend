package com.greenfoxacademy.ferrilatakryptonitetribesapplication.security;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BcryptTest {

  @Test
  public void emptyPasswordDoesNotMatchPassword() {
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    String result = encoder.encode("password");
    assertThat(encoder.matches("razzyawards", result)).isFalse();
  }

  @Test
  public void passwordMatches() {
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    String result = encoder.encode("password");
    assertThat(result.equals("password")).isFalse();
    assertThat(encoder.matches("password", result)).isTrue();
  }
}
