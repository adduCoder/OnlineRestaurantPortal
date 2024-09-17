package com.user.service;

import com.user.dto.AddressOutDto;
import com.user.dto.AmountInDto;
import com.user.dto.LoginInDto;
import com.user.dto.UserInDto;
import com.user.dto.UserOutDto;
import com.user.conversion.DtoConversion;
import com.user.entity.User;
import com.user.exception.NotFound;
import com.user.exception.UserAlreadyExisted;
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

  /**
   * Service for sending emails.
   * <p>
   * Used to send email notifications to users.
   * </p>
   */
  @Autowired
  private EmailService emailService;

  /**
   * Repository for accessing user data from the database.
   * <p>
   * This is used to perform CRUD operations on user entities.
   * </p>
   */
  @Autowired
  private UserRepo userRepo;


  /**
   * Service for managing addresses.
   * <p>
   * Used to interact with user addresses, including retrieving and deleting addresses associated with users.
   * </p>
   */
  @Autowired
  private AddressService addressService;

  /**
   * Formats a string by trimming whitespace and converting it to lowercase.
   *
   * @param currentString the string to be formatted
   * @return the formatted string, or the original string if it is null or empty
   */
  public String stringFormatter(final String currentString) {
    if (currentString == null || currentString.isEmpty()) {
      return currentString;
    }
    String formattedString = currentString.trim();
    formattedString = formattedString.replaceAll("\\s+", " ");
    return formattedString.toLowerCase();
  }


  /**
   * Retrieves a user by their ID.
   *
   * @param userId the ID of the user to retrieve
   * @return a {@link UserOutDto} representing the user with the specified ID
   */
  public UserOutDto getUser(final Integer userId) {
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

  /**
   * Adds a new user to the repository.
   *
   * @param userInDto the DTO containing user data to be added
   * @throws UserAlreadyExisted if a user with the same email already exists
   */
  public void addUser(final UserInDto userInDto) {
    log.info("Adding new user with email: {}", userInDto.getEmail());
    User newUser = DtoConversion.mapToUser(userInDto);
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

    if (newUser.getRole().equals(Role.OWNER)) {
      newUser.setWalletBalance(null);
    }
    userRepo.save(newUser);
    log.info("User added successfully: {}", newUser);
    UserApiResponse userApiResponse = new UserApiResponse();
    userApiResponse.setMessage(Constant.USER_CREATED_SUCCESS);
    userApiResponse.setUserId(newUser.getId());
    userApiResponse.setRole(newUser.getRole());
  }

  /**
   * Updates an existing user with new details.
   *
   * @param userId the ID of the user to be updated
   * @param userInDto the DTO containing updated user details
   * @throws NotFound if no user with the specified ID is found
   */
  public void updateUser(final Integer userId, final UserInDto userInDto) {
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
   * @throws NotFound if no user with the specified ID is found
   */
  public UserOutDto deleteUser(final Integer userId) {
    log.info("Deleting user with ID: {}", userId);
    Optional<User> optionalUser = userRepo.findById(userId);
    if (!optionalUser.isPresent()) {
      log.warn("No user found with ID: {}", userId);
      throw new NotFound(Constant.NO_CUSTOMER_FOUND);
    }
    User user = optionalUser.get();
    List<AddressOutDto> addressList = addressService.getAddressByUserId(userId);
    for (AddressOutDto addressOutDto : addressList) {
      addressService.deleteAddress(addressOutDto.getAddressId());
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
  public UserOutDto loginUser(final LoginInDto loginInDto) {
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

  /**
   * Deducts an amount from a user's wallet balance.
   *
   * @param userId the ID of the user whose balance is to be deducted
   * @param amountInDto the DTO containing the amount to be deducted
   * @throws NotFound if no user with the specified ID is found
   */
  public void deductMoney(final Integer userId, final AmountInDto amountInDto) {
    Optional<User> optionalUser = userRepo.findById(userId);
    if (!optionalUser.isPresent()) {
      throw new NotFound(Constant.NO_CUSTOMER_FOUND);
    }
    User user = optionalUser.get();
    user.setWalletBalance(user.getWalletBalance() - amountInDto.getMoney());
    userRepo.save(user);
  }

  /**
   * Adds an amount to a user's wallet balance.
   *
   * @param userId the ID of the user whose balance is to be updated
   * @param amountInDto the DTO containing the amount to be added
   * @throws NotFound if no user with the specified ID is found
   */
  public void addMoney(final Integer userId, final AmountInDto amountInDto) {
    Optional<User> optionalUser = userRepo.findById(userId);
    if (!optionalUser.isPresent()) {
      throw new NotFound(Constant.NO_CUSTOMER_FOUND);
    }
    User user = optionalUser.get();
    user.setWalletBalance(user.getWalletBalance() + amountInDto.getMoney());
    userRepo.save(user);
  }

  /**
   * Sends an email to a predefined list of recipients.
   *
   * @param text the content of the email
   * @throws NotFound if an error occurs while sending the email
   */
  public void sendMail(final String text) {
    try {
      List<String> recipients = Arrays.asList(
        "iadityapatel1729@gmail.com",
        "adityapatel21052022@gmail.com"
        //"vyaskhushi2407@gmail.com"
      );
      emailService.sendMail(Constant.SENDER, recipients, text);
    } catch (Exception e) {
      e.printStackTrace();
      throw new NotFound(Constant.NO_ADDRESS_FOUND);
    }
  }

}

