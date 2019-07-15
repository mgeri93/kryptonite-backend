package com.greenfoxacademy.ferrilatakryptonitetribesapplication.kingdom;

import com.greenfoxacademy.ferrilatakryptonitetribesapplication.applicationuser.ApplicationUserRepository;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.exception.customexceptions.UserRelatedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kingdom")
public class KingdomController {

  @Autowired
  private IKingdomRepository kingdomRepository;

  @Autowired
  private ApplicationUserRepository applicationUserRepository;

  @GetMapping("/")
  ResponseEntity<String> kingdom() {
    return new ResponseEntity<>("kingdom", HttpStatus.OK);
  }

  @GetMapping("/{id}")
  KingdomDTO getKingdomById(@PathVariable(name = "id") long id) {
    if (applicationUserRepository.existsById(id)) {

      return new KingdomDTO(id, kingdomRepository.findById(id).getName(),
          kingdomRepository.findById(id).getBuildings(), 
          kingdomRepository.findById(id).getTroops(),
          kingdomRepository.findById(id).getResourceList());
    }
    throw new UserRelatedException("No User with this id: " + id);
  }
}
