package com.user.service;

import com.user.dto.AddressOutDto;
import com.user.dto.AmountInDto;
import com.user.dto.LoginInDto;
import com.user.dto.UserInDto;
import com.user.dto.UserOutDto;
import com.user.dtoconversion.DtoConversion;
import com.user.entity.User;
import com.user.exceptionhandler.NotFound;
import com.user.exceptionhandler.UserAlreadyExisted;
import com.user.repository.UserRepo;
import com.user.util.Constant;
import com.user.util.PasswordEncoder;
import com.user.util.Role;
import com.user.util.UserApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
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
  private EmailService emailService;

  @Autowired
  private UserRepo userRepo;

  @Autowired
  private AddressService addressService;

  public String stringFormatter(String currentString) {
    if (currentString == null || currentString.isEmpty()) {
      return currentString;
    }
    currentString = currentString.trim();
    currentString = currentString.replaceAll("\\s+", " ");
    return currentString.toLowerCase();
  }


  /**
   * Retrieves a user by their ID.
   *
   * @param userId the ID of the user to retrieve
   * @return a {@link UserOutDto} representing the user with the specified ID
   */
  public UserOutDto getUser(Integer userId) {
    log.info("Fetching user with ID: {}", userId);
    Optional<User> optionalUser = userRepo.findById(userId);
    if (!optionalUser.isPresent()) {
      log.warn("No user found with ID: {}", userId);
      throw new NotFound(Constant.NO_CUSTOMER_FOUND);
    }
    User user = optionalUser.get();
    log.info("User found: {}", user);
    return DtoConversion.mapToUserOutDto(user);
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
      userOutDtoList.add(DtoConversion.mapToUserOutDto(user));
    }
    log.info("Fetched {} users", userOutDtoList.size());
    return userOutDtoList;
  }


  public void addUser(UserInDto userInDto) {
    log.info("Adding new user with email: {}", userInDto.getEmail());
    User newUser = DtoConversion.mapToUser(userInDto);
    // String encodedPassword = PasswordEncoder.encodePassword(newUser.getPassword());
    // newUser.setPassword(encodedPassword);
    // Decode Base64 password received from frontend
    String decodedPassword = PasswordEncoder.decodePassword(newUser.getPassword());
    String encodedPassword = PasswordEncoder.encodePassword(decodedPassword);

    newUser.setPassword(encodedPassword);

    newUser.setEmail(stringFormatter(newUser.getEmail()));
    newUser.setName(stringFormatter(newUser.getName()));

    Optional<User> existedUser = userRepo.findByEmail(newUser.getEmail());

    if (existedUser.isPresent()) {
      log.warn("User with email {} already exists", newUser.getEmail());
      throw new UserAlreadyExisted();
    }

    if (newUser.getRole().equals(Role.OWNER)) newUser.setWalletBalance(null);

    userRepo.save(newUser);
    log.info("User added successfully: {}", newUser);
    UserApiResponse userApiResponse = new UserApiResponse();
    userApiResponse.setMessage(Constant.USER_CREATED_SUCCESS);
    userApiResponse.setUserId(newUser.getId());
    userApiResponse.setRole(newUser.getRole());
  }


  public void updateUser(Integer userId, UserInDto userInDto) {
    log.info("Updating user with ID: {}", userId);
    Optional<User> optionalUser = userRepo.findById(userId);
    if (!optionalUser.isPresent()) {
      throw new NotFound(Constant.NO_CUSTOMER_FOUND);
    }
    User user = optionalUser.get();
    user.setName(userInDto.getName());
    user.setEmail(userInDto.getEmail());
    user.setPhoneNo(userInDto.getPhoneNo());
    userRepo.save(user);
    log.info("User updated successfully: {}", user);
  }

  /**
   * Deletes a user by their ID.
   *
   * @param userId the ID of the user to be deleted
   * @return a {@link UserOutDto} representing the deleted user
   */
  public UserOutDto deleteUser(Integer userId) {
    log.info("Deleting user with ID: {}", userId);
    Optional<User> optionalUser = userRepo.findById(userId);
    if (!optionalUser.isPresent()) {
      log.warn("No user found with ID: {}", userId);
      throw new NotFound(Constant.NO_CUSTOMER_FOUND);
    }
    User user = optionalUser.get();
    List<AddressOutDto> addressList = addressService.getAddressByUserId(userId);
    for (AddressOutDto AddressOutDto : addressList) {
      addressService.deleteAddress(AddressOutDto.getAddressId());
    }
    userRepo.delete(user);
    log.info("User deleted successfully: {}", user);
    return DtoConversion.mapToUserOutDto(user);
  }

  /**
   * Attempts to log in a user.
   *
   * @param loginInDto the login request containing email and password
   * @return a message indicating the result of the login attempt
   */
  public UserOutDto loginUser(LoginInDto loginInDto) {
    String email = loginInDto.getEmail();
    email = stringFormatter(email);
    String providedPassword = loginInDto.getPassword();

    Optional<User> optionalUser = userRepo.findByEmail(email);
    if (!optionalUser.isPresent()) {
      return null;
    }

    User user = optionalUser.get();
    String storedPassword = user.getPassword();
    String decodedStoredPassword = PasswordEncoder.decodePassword(storedPassword);
    String decodedProvidedPassword = PasswordEncoder.decodePassword(providedPassword);
    if (decodedStoredPassword.equals(decodedProvidedPassword)) {
      return DtoConversion.mapToUserOutDto(user);
    } else {
      return null;
    }
  }

  public void deductMoney(Integer userId, AmountInDto amountInDto) {
    Optional<User> optionalUser = userRepo.findById(userId);
    if (!optionalUser.isPresent()) {
      throw new NotFound(Constant.NO_CUSTOMER_FOUND);
    }
    User user = optionalUser.get();
    user.setWalletBalance(user.getWalletBalance() - amountInDto.getMoney());
    userRepo.save(user);
  }

  public void addMoney(Integer userId, AmountInDto amountInDto) {
    Optional<User> optionalUser = userRepo.findById(userId);
    if (!optionalUser.isPresent()) {
      throw new NotFound(Constant.NO_CUSTOMER_FOUND);
    }
    User user = optionalUser.get();
    user.setWalletBalance(user.getWalletBalance() + amountInDto.getMoney());
    userRepo.save(user);
  }

  public void sendMail(String text) {
    try {
      // Define the list of recipients
      List<String> recipients = Arrays.asList(
        "iadityapatel1729@gmail.com",
        "adityapatel21052022@gmail.com"
        //"vyaskhushi2407@gmail.com"
      );
      // Send email to all recipients
      emailService.sendMail(Constant.SENDER, recipients, text);
    } catch (Exception e) {
      e.printStackTrace();
      throw new NotFound(Constant.NO_ADDRESS_FOUND);
    }
  }

}

