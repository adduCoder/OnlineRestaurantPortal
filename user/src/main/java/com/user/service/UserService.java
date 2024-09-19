package com.user.service;

import com.user.dto.AddressOutDto;
import com.user.dto.AmountInDto;
import com.user.dto.LoginInDto;
import com.user.dto.MessageOutDto;
import com.user.dto.UserInDto;
import com.user.dto.UserOutDto;
import com.user.dto.ContactUsInDto;
import com.user.conversion.DtoConversion;
import com.user.entity.User;
import com.user.exception.InvalidPasswordException;
import com.user.exception.NotFoundException;
import com.user.exception.UserAlreadyExisted;
import com.user.repository.UserRepository;
import com.user.util.Constant;
import com.user.util.PasswordEncoder;
import com.user.util.Role;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

/**
 * Service class for managing users.
 * <p>
 * This service provides methods for creating, retrieving, updating, deleting users, and handling user logins.
 * It interacts with the {@link UserRepository} repository to perform CRUD operations and uses
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
  private UserRepository userRepository;


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
    return formattedString.toLowerCase(Locale.ROOT);
  }


  /**
   * Retrieves a user by their ID.
   *
   * @param userId the ID of the user to retrieve
   * @return a {@link UserOutDto} representing the user with the specified ID
   */
  public UserOutDto getUser(final Integer userId) {
    log.info("Fetching user with ID: {}", userId);
    Optional<User> optionalUser = userRepository.findById(userId);
    if (!optionalUser.isPresent()) {
      log.warn("No user found with ID: {}", userId);
      throw new NotFoundException(Constant.NO_CUSTOMER_FOUND);
    }
    User user = optionalUser.get();
    log.info("User found with id: {}", user.getId());
    return DtoConversion.mapToUserOutDto(user);
  }

  /**
   * Retrieves all users.
   *
   * @return a list of {@link UserOutDto} representing all users
   */
  public List<UserOutDto> getAllUser() {
    log.info("Fetching all users");
    List<User> userList = userRepository.findAll();
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

    String decodedPassword;
    try {
      decodedPassword = PasswordEncoder.decodePassword(newUser.getPassword());
    } catch (IllegalArgumentException e) {
      log.info("Password does not appear to be Base64 encoded. Using plain text password.");
      decodedPassword = newUser.getPassword();
    }
    System.out.println(decodedPassword);
    if (decodedPassword == null
      || !decodedPassword.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{4,}$")) {
      throw new InvalidPasswordException(Constant.INVALID_PASSWORD);
    }

    String encodedPassword = PasswordEncoder.encodePassword(decodedPassword);
    newUser.setPassword(encodedPassword);

    newUser.setEmail(stringFormatter(newUser.getEmail()));
    newUser.setName(stringFormatter(newUser.getName()));

    Optional<User> existedUser = userRepository.findByEmail(newUser.getEmail());
    if (existedUser.isPresent()) {
      log.warn("User with email {} already exists", newUser.getEmail());
      throw new UserAlreadyExisted();
    }

    if (newUser.getRole().equals(Role.OWNER)) {
      newUser.setWalletBalance(null);
    }

    userRepository.save(newUser);
    log.info("Successfully added user with name: {}", newUser.getName());
  }

  /**
   * Updates an existing user with new details.
   *
   * @param userId the ID of the user to be updated
   * @param userInDto the DTO containing updated user details
   * @throws NotFoundException if no user with the specified ID is found
   */
  public void updateUser(final Integer userId, final UserInDto userInDto) {
    log.info("Updating user with ID: {}", userId);
    Optional<User> optionalUser = userRepository.findById(userId);

    if (!optionalUser.isPresent()) {
      throw new NotFoundException(Constant.NO_CUSTOMER_FOUND);
    }

    User user = optionalUser.get();
    user.setName(userInDto.getName());
    user.setEmail(userInDto.getEmail());
    user.setPhoneNo(userInDto.getPhoneNo());

    userRepository.save(user);
    log.info("User updated successfully: {}", user.getName());
  }

  /**
   * Deletes a user by their ID.
   *
   * @param userId the ID of the user to be deleted
   * @return a {@link UserOutDto} representing the deleted user
   * @throws NotFoundException if no user with the specified ID is found
   */
  public UserOutDto deleteUser(final Integer userId) {
    log.info("Deleting user with ID: {}", userId);
    Optional<User> optionalUser = userRepository.findById(userId);

    if (!optionalUser.isPresent()) {
      log.error("No user found with ID: {}", userId);
      throw new NotFoundException(Constant.NO_CUSTOMER_FOUND);
    }

    User user = optionalUser.get();
    List<AddressOutDto> addressList = addressService.getAddressByUserId(userId);
    for (AddressOutDto addressOutDto : addressList) {
      addressService.deleteAddress(addressOutDto.getAddressId());
    }

    userRepository.delete(user);
    log.info("User deleted successfull: {}", user.getName());
    return DtoConversion.mapToUserOutDto(user);
  }

  /**
   * Attempts to log in a user.
   *
   * @param loginInDto the login request containing email and password
   * @return a message indicating the result of the login attempt
   */
  public UserOutDto loginUser(final LoginInDto loginInDto) {
    log.info("Trying to login with email: {}", loginInDto.getEmail());

    // Normalize the email
    String email = stringFormatter(loginInDto.getEmail());
    String providedPassword = loginInDto.getPassword();

    // Find user by email
    Optional<User> optionalUser = userRepository.findByEmail(email);
    if (!optionalUser.isPresent()) {
      log.error("User with email {} does not exist", email);
      return null;
    }
    User user = optionalUser.get();
    String storedPassword = user.getPassword();
    String decodedStoredPassword;
    String decodedProvidedPassword;

    try {
      decodedStoredPassword = PasswordEncoder.decodePassword(storedPassword);
    } catch (IllegalArgumentException e) {
      decodedStoredPassword = storedPassword;
    }
    try {
      decodedProvidedPassword = PasswordEncoder.decodePassword(providedPassword);
    } catch (IllegalArgumentException e) {
      decodedProvidedPassword = providedPassword;
    }
    if (decodedStoredPassword.equals(decodedProvidedPassword)) {
      log.info("Successfully logged in as: {}", email);
      return DtoConversion.mapToUserOutDto(user);
    } else {
      log.error("Invalid credentials for email: {}", email);
      return null;
    }
  }


  /**
   * Deducts an amount from a user's wallet balance.
   *
   * @param userId the ID of the user whose balance is to be deducted
   * @param amountInDto the DTO containing the amount to be deducted
   * @throws NotFoundException if no user with the specified ID is found
   */
  public void deductMoney(final Integer userId, final AmountInDto amountInDto) {
    log.info("Trying to deduct {} from user with id {} ", amountInDto.getMoney(), userId);
    Optional<User> optionalUser = userRepository.findById(userId);
    if (!optionalUser.isPresent()) {
      log.error("User not found with id :{}", userId);
      throw new NotFoundException(Constant.NO_CUSTOMER_FOUND);
    }
    User user = optionalUser.get();
    user.setWalletBalance(user.getWalletBalance() - amountInDto.getMoney());
    log.info("Successfully deducted : {} from userId : {}", amountInDto.getMoney(), user.getId());
    userRepository.save(user);
  }

  /**
   * Adds an amount to a user's wallet balance.
   *
   * @param userId the ID of the user whose balance is to be updated
   * @param amountInDto the DTO containing the amount to be added
   * @throws NotFoundException if no user with the specified ID is found
   */
  public void addMoney(final Integer userId, final AmountInDto amountInDto) {
    log.info("Trying to add {} Rs. ", amountInDto.getMoney());
    Optional<User> optionalUser = userRepository.findById(userId);
    if (!optionalUser.isPresent()) {
      log.error("No such user found with id: {}", userId);
      throw new NotFoundException(Constant.NO_CUSTOMER_FOUND);
    }
    User user = optionalUser.get();
    user.setWalletBalance(user.getWalletBalance() + amountInDto.getMoney());
    log.info("Successfully added {} Rs. ", amountInDto.getMoney());
    userRepository.save(user);
  }

  /**
   * Sends an email in response to a "Contact Us" form submission.
   * <p>
   * This method sends an email to the support team using the details provided
   * in the {@code contactUsRequest}. It sends the email to a list of predefined support
   * email addresses.
   * </p>
   *
   * @param contactUsInDto the request containing the customer's name, subject, and message
   * @return a {@link MessageOutDto} indicating whether the email was sent successfully
   */
  public MessageOutDto sendContactUsEmail(final ContactUsInDto contactUsInDto) {
    List<String> supportEmails = Arrays.asList("iadityapatel1729@gmail.com",
      "adityapatel2105222@gmail.com");

    String subject = contactUsInDto.getSubject();
    String customerName = contactUsInDto.getName();
    String customMessage = contactUsInDto.getMessage();

    emailService.sendContactUsEmail(supportEmails, subject, customerName, customMessage);
    return new MessageOutDto(Constant.MAIL_SENDED_SUCCESS);
  }
}

