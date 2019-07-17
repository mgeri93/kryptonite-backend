package com.greenfoxacademy.ferrilatakryptonitetribesapplication.applicationuser;

import com.greenfoxacademy.ferrilatakryptonitetribesapplication.kingdom.Kingdom;
import java.util.List;
import org.springframework.http.ResponseEntity;

public interface ApplicationUserService {

  List<ApplicationUser> findAllUser();

  void saveUser(ApplicationUser applicationUser);

  boolean credentialsProvided(String username, String password);

  boolean validCredentials(String username, String password);

  ResponseEntity loginResponse(String username, String password);

  ResponseEntity loginResponseWithValidCredentials(String username, String password);

  List<Kingdom> getKingdomListByUserId(long id);

}
