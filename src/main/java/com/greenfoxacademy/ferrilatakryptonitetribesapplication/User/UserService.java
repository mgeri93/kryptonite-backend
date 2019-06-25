package com.greenfoxacademy.ferrilatakryptonitetribesapplication.User;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  private UserRepository userRepository;

  @Autowired
  public UserService(UserRepository userRepository){
    this.userRepository = userRepository;
  }

  public boolean isValidUser(User user){
    return (user.getUsername() != null && !user.getUsername().equals("") );
  }

  public boolean isExistingUser(User user) {
    return userRepository.existsByUsername(user.getUsername());
  }

  public void saveUser(User user){
    userRepository.save(user);
  }

  public Optional<User> findById(long id){
    return userRepository.findById(id);
  }

}
