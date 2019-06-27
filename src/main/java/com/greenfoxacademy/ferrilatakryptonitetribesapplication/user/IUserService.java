package com.greenfoxacademy.ferrilatakryptonitetribesapplication.user;

import java.util.List;

public interface IUserService {

  List<User> findAllUser();

  void saveUser(User user);

  boolean credentialsProvided(String username, String password);

  boolean validCredentials(String username, String password);

  Object loginResponse(String username, String password);

}
