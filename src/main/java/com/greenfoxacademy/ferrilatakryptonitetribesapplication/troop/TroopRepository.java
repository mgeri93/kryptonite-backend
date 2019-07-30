package com.greenfoxacademy.ferrilatakryptonitetribesapplication.troop;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface TroopRepository extends CrudRepository<Troop, Long> {

  Troop findTroopById(long id);

  Troop findTroopByLevel(long level);

}
