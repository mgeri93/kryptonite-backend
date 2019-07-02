package com.greenfoxacademy.ferrilatakryptonitetribesapplication.user;

import com.greenfoxacademy.ferrilatakryptonitetribesapplication.kingdom.IKingdomRepository;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.kingdom.Kingdom;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.user.dto.ErrorMessage;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.user.dto.UserDTO;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.user.dto.UserWithKingdomDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements IUserService {

  private UserRepository userRepository;
  private IKingdomRepository kingdomRepository;

  @Autowired
  public UserServiceImp(UserRepository userRepository, IKingdomRepository kingdomRepository) {
    this.userRepository = userRepository;
    this.kingdomRepository = kingdomRepository;
  }

  public boolean isValidUser(User user) {
    return (user.getUsername() != null && !user.getUsername().equals(""));
  }

  public boolean isExistingUser(User user) {
    return userRepository.existsByUsername(user.getUsername());
  }

  @Override
  public List<User> findAllUser() {
    List<User> users = new ArrayList<>();
    userRepository.findAll().forEach(user -> users.add(user));
    return users;
  }

  @Override
  public void saveUser(User user) {
    userRepository.save(user);
  }

  public Optional<User> findUserById(long id) {
    return userRepository.findById(id);
  }

  public ResponseEntity registerNewUser(UserDTO userDTO) {
    String userName = userDTO.getUsername();
    String password = userDTO.getPassword();

    if ((userName == null || userName.isEmpty()) && (password == null || password.isEmpty())) {
      return ResponseEntity.status(400)
          .body(new ErrorMessage("Missing parameters: username, password!"));
    } else if (password == null || password.isEmpty()) {
      return ResponseEntity.status(400).body(new ErrorMessage("Missing parameter: password!"));
    } else if (userName == null || userName.isEmpty()) {
      return ResponseEntity.status(400).body(new ErrorMessage("Missing parameter: username!"));
    } else if (userRepository.existsByUsername(userName)) {
      return ResponseEntity.status(409).body(new ErrorMessage("Username already taken!"));
    } else {
      User userToBeSaved = createUserFromDTO(userDTO);
      Kingdom kingdom = createKingdom(userDTO.getKingdom(), userName);
      kingdom.setUser(userToBeSaved);
      kingdomRepository.save(kingdom);
      userRepository.save(userToBeSaved);
      return ResponseEntity.status(200)
          .body(
              new UserWithKingdomDTO(
                  userToBeSaved.getId(), userToBeSaved.getUsername(), kingdom.getId()));
    }
  }

  public User createUserFromDTO(UserDTO userDTO) {
    ModelMapper modelMapper = new ModelMapper();
    return modelMapper.map(userDTO, User.class);
  }

  public Kingdom createKingdom(String kingdomName, String username) {
    if (isKingdomNameNullOrEmpty(kingdomName)) {
      return new Kingdom(String.format("%s's kingdom", username));
    }
    return new Kingdom(kingdomName);
  }

  public Boolean isKingdomNameNullOrEmpty(String kingdomName) {
    return kingdomName == null || kingdomName.isEmpty();
  }
}
