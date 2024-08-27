package com.user.controller;

import com.user.indto.LoginInDto;
import com.user.indto.UserInDto;
import com.user.outdto.UserOutDto;
import com.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * Controller for managing users.
 * Provides endpoints for user management operations including creation, retrieval, update, deletion, and login.
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {


  @Autowired
  private UserService userService;

  /**
   * Fetches a user by their ID.
   *
   * @param userId the ID of the user to fetch
   * @return ResponseEntity containing the user details and HTTP status
   */
  @GetMapping("/get/{userId}")
  public ResponseEntity<?> getUser(@PathVariable Integer userId) {
    log.info("Fetching user with ID: {}", userId);
    UserOutDto userOutDto = userService.getUser(userId);
    return new ResponseEntity<>(userOutDto, HttpStatus.OK);
  }

  /**
   * Retrieves all users.
   *
   * @return ResponseEntity containing a list of all users and HTTP status
   */
  @GetMapping("/getAll")
  public ResponseEntity<?> getAllUser() {
    log.info("Getting All Users");
    List<UserOutDto> allUserOutDto = userService.getAllUser();
    log.info("Fetched {} users", allUserOutDto.size());
    return new ResponseEntity<>(allUserOutDto, HttpStatus.OK);
  }

  /**
   * Adds a new user.
   *
   * @param userInDto the user information to add
   * @return ResponseEntity containing the ID of the created user and HTTP status
   */
  @PostMapping("/add")
  public ResponseEntity<?> addUser(@Valid @RequestBody UserInDto userInDto) {
    log.info("Adding new user with email: {}", userInDto.getEmail());
    Integer createdUser = userService.addUser(userInDto);
    if (createdUser == null) {
      log.error("Failed to add user with email: {}", userInDto.getEmail());
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    log.info("User added successfully with ID: {}", createdUser);
    return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
  }

  /**
   * Updates an existing user.
   *
   * @param userId the ID of the user to update
   * @param userInDto the new user information
   * @return ResponseEntity containing the updated user details and HTTP status
   */
  @PutMapping("/update/{userId}")
  public ResponseEntity<?> updateUser(@PathVariable Integer userId, @Valid @RequestBody UserInDto userInDto) {
    log.info("Updating user with ID: {}", userId);
    UserOutDto userOutDto = userService.updateUser(userId, userInDto);
    log.info("User updated successfully with ID: {}", userId);
    return new ResponseEntity<>(userOutDto, HttpStatus.OK);
  }

  /**
   * Deletes a user by their ID.
   *
   * @param userId the ID of the user to delete
   * @return ResponseEntity containing the deleted user details and HTTP status
   */
  @DeleteMapping("/delete/{userId}")
  public ResponseEntity<?> deleteUser(@PathVariable Integer userId) {
    log.info("Deleting user with ID: {}", userId);
    UserOutDto userOutDto = userService.deleteUser(userId);
    log.info("User deleted successfully with ID: {}", userId);
    return new ResponseEntity<>(userOutDto, HttpStatus.OK);
  }

  /**
   * Logs in a user.
   *
   * @param loginInDto the login credentials
   * @return ResponseEntity containing a login response and HTTP status
   */
  @PostMapping("/login")
  public ResponseEntity<?> loginUser(@Valid @RequestBody LoginInDto loginInDto) {
    log.info("Attempting login for user with email: {}", loginInDto.getEmail());
    String res = userService.loginUser(loginInDto);
    log.info("Login successful for user with email: {}", loginInDto.getEmail());
    return new ResponseEntity<>(res, HttpStatus.OK);
  }

}


