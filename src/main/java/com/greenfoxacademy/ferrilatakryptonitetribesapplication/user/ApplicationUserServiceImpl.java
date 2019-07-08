package com.greenfoxacademy.ferrilatakryptonitetribesapplication.user;

import com.greenfoxacademy.ferrilatakryptonitetribesapplication.building.Building;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.building.BuildingFactory;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.building.BuildingType;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.kingdom.IKingdomRepository;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.kingdom.Kingdom;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.resource.Gold;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.user.dto.ErrorMessage;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.user.dto.UserWithKingdomDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ApplicationUserServiceImpl implements ApplicationUserService {

  private ApplicationUserRepository applicationUserRepository;
  private IKingdomRepository kingdomRepository;

  @Autowired
  public ApplicationUserServiceImpl(ApplicationUserRepository applicationUserRepository,
      IKingdomRepository kingdomRepository) {
    this.applicationUserRepository = applicationUserRepository;
    this.kingdomRepository = kingdomRepository;
  }

  public boolean isValidUser(ApplicationUser applicationUser) {
    return (applicationUser.getUsername() != null && !applicationUser.getUsername().equals(""));
  }

  public boolean isExistingUser(ApplicationUser applicationUser) {
    return applicationUserRepository.existsByUsername(applicationUser.getUsername());
  }

  @Override
  public List<ApplicationUser> findAllUser() {
    List<ApplicationUser> applicationUsers = new ArrayList<>();
    applicationUserRepository.findAll().forEach(user -> applicationUsers.add(user));
    return applicationUsers;
  }

  @Override
  public void saveUser(ApplicationUser applicationUser) {
    applicationUserRepository.save(applicationUser);
  }

  public Optional<ApplicationUser> findUserById(long id) {
    return applicationUserRepository.findById(id);
  }

  public ResponseEntity registerNewUser(ApplicationUserDTO applicationUserDTO) {
    String userName = applicationUserDTO.getUsername();
    String password = applicationUserDTO.getPassword();
    if (!credentialsProvided(userName, password)) {
      return registerUserWithMissingCredentials(applicationUserDTO);
    } else if (applicationUserRepository.existsByUsername(userName)) {
      return ResponseEntity.status(409).body(new ErrorMessage("Username already taken!"));
    } else {
      ApplicationUser applicationUserToBeSaved = createUserFromDTO(applicationUserDTO);
      Kingdom kingdom = initKingdom(createKingdom(applicationUserDTO.getKingdom(),
          new ApplicationUser(applicationUserToBeSaved.getUsername(),
              applicationUserToBeSaved.getPassword())));
      kingdom.setApplicationUser(applicationUserToBeSaved);
      kingdomRepository.save(kingdom);
      applicationUserRepository.save(applicationUserToBeSaved);
      return ResponseEntity.status(200).body(new UserWithKingdomDTO(
                  applicationUserToBeSaved.getId(), applicationUserToBeSaved.getUsername(),
              kingdom.getId()));
    }
  }

  public ResponseEntity registerUserWithMissingCredentials(ApplicationUserDTO applicationUserDTO) {
    String userName = applicationUserDTO.getUsername();
    String password = applicationUserDTO.getPassword();
    if ((userName == null || userName.isEmpty()) && (password == null || password.isEmpty())) {
      return ResponseEntity.status(400)
          .body(new ErrorMessage("Missing parameters: username, password!"));
    } else if (password == null || password.isEmpty()) {
      return ResponseEntity.status(400).body(new ErrorMessage("Missing parameter: password!"));
    } else {
      return ResponseEntity.status(400).body(new ErrorMessage("Missing parameter: username!"));
    }
  }

  public ApplicationUser createUserFromDTO(ApplicationUserDTO applicationUserDTO) {
    return new ModelMapper().map(applicationUserDTO, ApplicationUser.class);
  }

  public Kingdom initKingdom(Kingdom kingdom) {
    Gold startingGold =  new Gold(100, kingdom);
    kingdom.getResourceList().add(0,startingGold);
    for (BuildingType buildingType : BuildingType.values()) {
      kingdom.getBuildings().add(BuildingFactory.createBuilding(buildingType));
    }
    for (Building building : kingdom.getBuildings()) {
      building.setKingdom(kingdom);
    }
    return kingdom;
  }

  public Kingdom createKingdom(String kingdomName, ApplicationUser applicationUser) {
    if (isKingdomNameNullOrEmpty(kingdomName)) {
      return new Kingdom(String.format("%s's kingdom", applicationUser.getUsername()),
          applicationUser);
    }
    return new Kingdom(kingdomName, applicationUser);
  }

  public Boolean isKingdomNameNullOrEmpty(String kingdomName) {
    return kingdomName == null || kingdomName.isEmpty();
  }

  @Override
  public boolean credentialsProvided(String username, String password) {
    return (username != null && !username.equals("") && password != null && !password.equals(""));
  }

  @Override
  public boolean validCredentials(String username, String password) {
    if (applicationUserRepository.existsByUsername(username)) {
      return applicationUserRepository.findByUsername(username).getPassword().equals(password);
    } else {
      return false;
    }
  }

  @Override
  public ResponseEntity loginResponse(String username, String password) {

    if (!credentialsProvided(username, password)) {
      return loginResponseWithValidCredentials(username, password);
    }
    if (validCredentials(username, password)) {
      return new ResponseEntity<>(applicationUserRepository.findByUsername(username),
          HttpStatus.OK);
    }
    if (!applicationUserRepository.existsByUsername(username)) {
      return new ResponseEntity<>("No such applicationUser: " + username + "!",
          HttpStatus.UNAUTHORIZED);
    } else {
      return new ResponseEntity<>("Wrong password!", HttpStatus.UNAUTHORIZED);
    }
  }

  @Override
  public ResponseEntity loginResponseWithValidCredentials(String username, String password) {
    if ((username.equals("")) && (password.equals(""))) {
      return new ResponseEntity<>(
          "Missing parameter(s): username, password", HttpStatus.BAD_REQUEST);
    } else if ((username.equals(""))) {
      return new ResponseEntity<>("Missing parameter(s): username", HttpStatus.BAD_REQUEST);
    } else {
      return new ResponseEntity<>("Missing parameter(s): password", HttpStatus.BAD_REQUEST);
    }
  }
}
