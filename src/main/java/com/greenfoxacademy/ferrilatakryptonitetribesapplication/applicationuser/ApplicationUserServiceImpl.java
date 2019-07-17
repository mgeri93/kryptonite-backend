package com.greenfoxacademy.ferrilatakryptonitetribesapplication.applicationuser;

import com.greenfoxacademy.ferrilatakryptonitetribesapplication.applicationuser.dto.UserWithKingdomDTO;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.building.Building;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.building.BuildingFactory;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.building.BuildingType;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.exception.customexceptions.UserRelatedException;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.kingdom.IKingdomRepository;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.kingdom.Kingdom;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.resource.Gold;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.security.JwtAuthenticationFilter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ApplicationUserServiceImpl implements ApplicationUserService {
  private BCryptPasswordEncoder encoder;
  private ApplicationUserRepository applicationUserRepository;
  private IKingdomRepository kingdomRepository;

  @Autowired
  public ApplicationUserServiceImpl(ApplicationUserRepository applicationUserRepository,
      IKingdomRepository kingdomRepository, BCryptPasswordEncoder encoder) {
    this.applicationUserRepository = applicationUserRepository;
    this.kingdomRepository = kingdomRepository;
    this.encoder = encoder;
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
    applicationUserDTO.setPassword(encoder.encode(applicationUserDTO.getPassword()));
    if (!credentialsProvided(applicationUserDTO.getUsername(), applicationUserDTO.getPassword())) {
      return registerUserWithMissingCredentials(applicationUserDTO);
    } else if (applicationUserRepository.existsByUsername(userName)) {
      throw new UserRelatedException("Username already taken, choose another one!");
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
      throw new UserRelatedException("Invalid user details provided.");
    } else if (password == null || password.isEmpty()) {
      throw new UserRelatedException("Missing parameter: password");
    } else {
      throw new UserRelatedException("Missing parameter: username");
    }
  }

  public ApplicationUser createUserFromDTO(ApplicationUserDTO applicationUserDTO) {
    return new ModelMapper().map(applicationUserDTO, ApplicationUser.class);
  }

  public Kingdom initKingdom(Kingdom kingdom) {
    Gold startingGold = new Gold(100, kingdom);
    kingdom.getResourceList().add(0, startingGold);
    for (BuildingType buildingType : BuildingType.values()) {
      kingdom.getBuildings().add(BuildingFactory.createBuilding(buildingType));
      kingdom.getBuildings().get(kingdom.getBuildings().size() - 1).setBuildingType(buildingType);
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

      return encoder.matches(password, applicationUserRepository.findByUsername(username).getPassword());
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
      throw new UserRelatedException("No such user in database, please register first");
    } else {
      throw new UserRelatedException("Invalid password, please try to log-in again.");
    }
  }

  @Override
  public ResponseEntity loginResponseWithValidCredentials(String username, String password) {
    if ((username.equals("")) && (password.equals(""))) {
      throw new UserRelatedException("Missing parameter(s): username, password");
    } else if ((username.equals(""))) {
      throw new UserRelatedException("Missing parameter(s): username");
    } else {
      throw new UserRelatedException("Missing parameter(s): password");
    }
  }
}
