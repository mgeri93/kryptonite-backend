package com.greenfoxacademy.ferrilatakryptonitetribesapplication.user;

import com.greenfoxacademy.ferrilatakryptonitetribesapplication.exception.UserRelatedException;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.exception.WrongPasswordException;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.kingdom.IKingdomRepository;
import com.greenfoxacademy.ferrilatakryptonitetribesapplication.kingdom.Kingdom;
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
public class UserServiceImpl implements UserService {

  private UserRepository userRepository;
  private IKingdomRepository kingdomRepository;

  @Autowired
  public UserServiceImpl(UserRepository userRepository, IKingdomRepository kingdomRepository) {
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

    if (!credentialsProvided(userName, password)) {
      return registerUserWithMissingCredentials(userDTO);
    } else if (userRepository.existsByUsername(userName)) {
      throw new UserRelatedException("Username already taken, please choose another one!");
    } else {
      User userToBeSaved = createUserFromDTO(userDTO);
      Kingdom kingdom = createKingdom(userDTO.getKingdom(), userName);
      kingdom.setUser(userToBeSaved);
      kingdomRepository.save(kingdom);
      userRepository.save(userToBeSaved);
      return ResponseEntity.status(200)
          .body(new UserWithKingdomDTO(
                  userToBeSaved.getId(), userToBeSaved.getUsername(), kingdom.getId()));
    }
  }

  public ResponseEntity registerUserWithMissingCredentials(UserDTO userDTO) {
    String userName = userDTO.getUsername();
    String password = userDTO.getPassword();
    if ((userName == null || userName.isEmpty()) && (password == null || password.isEmpty())) {
      return ResponseEntity.status(400)
          .body(new ErrorMessage("Missing parameters: username, password!"));
    } else if (password == null || password.isEmpty()) {
      return ResponseEntity.status(400).body(new ErrorMessage("Missing parameter: password!"));
    } else {
      return ResponseEntity.status(400).body(new ErrorMessage("Missing parameter: username!"));
    }
  }

  public User createUserFromDTO(UserDTO userDTO) {
    return new ModelMapper().map(userDTO, User.class);
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

  @Override
  public boolean credentialsProvided(String username, String password) {
    return (username != null && !username.equals("") && password != null && !password.equals(""));
  }

  @Override
  public boolean validCredentials(String username, String password) {
    if (userRepository.existsByUsername(username)) {
      return userRepository.findByUsername(username).getPassword().equals(password);
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
      return new ResponseEntity<>(userRepository.findByUsername(username), HttpStatus.OK);
    }
    if (!userRepository.existsByUsername(username)) {
      throw new NoSuchUserException("No such user in the database! Please register first!");
    } else {
      throw new WrongPasswordException("Invalid password, please try to log-in again.");
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
