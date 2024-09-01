package com.user.service;

import com.user.dtoconversion.DtoConversion;
import com.user.entity.User;
import com.user.exceptionhandler.NoCustomerFound;
import com.user.exceptionhandler.UserAlreadyExisted;
import com.user.indto.LoginInDto;
import com.user.indto.UserInDto;
import com.user.outdto.AddressResponse;
import com.user.outdto.UserOutDto;
import com.user.repository.UserRepo;
import com.user.util.PasswordEncoder;
import com.user.util.Role;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Service class for managing users.
 * <p>
 * This service provides methods for creating, retrieving, updating, deleting users, and handling user logins.
 * It interacts with the {@link UserRepo} repository to perform CRUD operations and uses
 * {@link DtoConversion} for converting between DTOs and entity objects. It also interacts with
 * {@link AddressService} for managing user addresses.
 * </p>
 */
@Slf4j
@Service
public class UserService {

  @Autowired
  private UserRepo userRepo;

  @Autowired
  private AddressService addressService;


  /**
   * Retrieves a user by their ID.
   *
   * @param userId the ID of the user to retrieve
   * @return a {@link UserOutDto} representing the user with the specified ID
   * @throws NoCustomerFound if no user with the given ID is found
   */
  public UserOutDto getUser(Integer userId) {
    log.info("Fetching user with ID: {}", userId);
    Optional<User> optionalUser = userRepo.findById(userId);
    if (!optionalUser.isPresent()) {
      log.warn("No user found with ID: {}", userId);
      throw new NoCustomerFound();
    }
    User user = optionalUser.get();
    log.info("User found: {}", user);
    return DtoConversion.userToResponse(user);
  }

  /**
   * Retrieves all users.
   *
   * @return a list of {@link UserOutDto} representing all users
   */
  public List<UserOutDto> getAllUser() {
    log.info("Fetching all users");
    List<User> userList = userRepo.findAll();
    List<UserOutDto> userOutDtoList = new ArrayList<>();
    for (User user : userList) {
      userOutDtoList.add(DtoConversion.userToResponse(user));
    }
    log.info("Fetched {} users", userOutDtoList.size());
    return userOutDtoList;
  }

  /**
   * Adds a new user.
   *
   * @param userInDto the request containing the details of the user to be added
   * @return the ID of the newly added user
   * @throws UserAlreadyExisted if a user with the given email already exists
   */
  public Integer addUser(UserInDto userInDto) {
    log.info("Adding new user with email: {}", userInDto.getEmail());
    User newUser = DtoConversion.requestToUser(userInDto);
   // String encodedPassword = PasswordEncoder.encodePassword(newUser.getPassword());
   // newUser.setPassword(encodedPassword);
    // Decode Base64 password received from frontend
    String decodedPassword = PasswordEncoder.decodePassword(newUser.getPassword());
    String encodedPassword = PasswordEncoder.encodePassword(decodedPassword);

    newUser.setPassword(encodedPassword);
    Optional<User> existedUser = userRepo.findByEmail(newUser.getEmail());
    if (existedUser.isPresent()) {
      log.warn("User with email {} already exists", newUser.getEmail());
      throw new UserAlreadyExisted();
    }
    if (newUser.getRole().equals(Role.OWNER)) newUser.setWalletBalance(null);
    userRepo.save(newUser);
    log.info("User added successfully: {}", newUser);
    return newUser.getId();
  }

  /**
   * Updates an existing user.
   *
   * @param userId the ID of the user to be updated
   * @param userInDto the request containing updated user details
   * @return a {@link UserOutDto} representing the updated user
   * @throws NoCustomerFound if no user with the given ID is found
   */
  public UserOutDto updateUser(Integer userId, UserInDto userInDto) {
    log.info("Updating user with ID: {}", userId);
    Optional<User> optionalUser = userRepo.findById(userId);
    if (!optionalUser.isPresent()) {
      log.warn("No user found with ID: {}", userId);
      throw new NoCustomerFound();
    }
    User user = optionalUser.get();
    user.setName(userInDto.getName());
    user.setEmail(userInDto.getEmail());
    user.setPhoneNo(userInDto.getPhoneNo());
    userRepo.save(user);
    log.info("User updated successfully: {}", user);
    return DtoConversion.userToResponse(user);
  }

  /**
   * Deletes a user by their ID.
   *
   * @param userId the ID of the user to be deleted
   * @return a {@link UserOutDto} representing the deleted user
   * @throws NoCustomerFound if no user with the given ID is found
   */
  public UserOutDto deleteUser(Integer userId) {
    log.info("Deleting user with ID: {}", userId);
    Optional<User> optionalUser = userRepo.findById(userId);
    if (!optionalUser.isPresent()) {
      log.warn("No user found with ID: {}", userId);
      throw new NoCustomerFound();
    }
    User user = optionalUser.get();
    List<AddressResponse> addressList = addressService.getAddressByUserId(userId);
    for (AddressResponse addressResponse:addressList) {
      addressService.deleteAddress(addressResponse.getAddressId());
    }
    userRepo.delete(user);
    log.info("User deleted successfully: {}", user);
    return DtoConversion.userToResponse(user);
  }

  /**
   * Attempts to log in a user.
   *
   * @param loginInDto the login request containing email and password
   * @return a message indicating the result of the login attempt
   */
  public UserOutDto loginUser(LoginInDto loginInDto) {
    String email = loginInDto.getEmail();
    String providedPassword = loginInDto.getPassword(); // Password provided by the user

    // Fetch user by email
    Optional<User> optionalUser = userRepo.findByEmail(email);
    if (!optionalUser.isPresent()) {
      // Email not found, return null to indicate failure
      return null;
    }

    User user = optionalUser.get();
    String storedPassword = user.getPassword(); // Encoded password from the database
    System.out.println(storedPassword+" "+providedPassword);
    // Decode both passwords for comparison
    String decodedStoredPassword = PasswordEncoder.decodePassword(storedPassword);
    String decodedProvidedPassword = PasswordEncoder.decodePassword(providedPassword);
    System.out.println(decodedStoredPassword+" "+decodedProvidedPassword);
    // Check if the decoded passwords match
    if (decodedStoredPassword.equals(decodedProvidedPassword)) {
      return DtoConversion.userToResponse(user); // Convert user to DTO
    } else {
      // Password mismatched, return null to indicate failure
      return null;
    }
  }
}

