package com.greenfoxacademy.ferrilatakryptonitetribesapplication.troop;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TroopDTO {

  private long level;
  private long hp;
  private long attack;
  private long defense;

}
