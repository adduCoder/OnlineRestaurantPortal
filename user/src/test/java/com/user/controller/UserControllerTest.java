package com.user.controller;

import com.user.dto.AmountInDto;
import com.user.dto.ApiResponse;
import com.user.dto.LoginInDto;
import com.user.dto.UserInDto;
import com.user.dto.UserOutDto;
import com.user.exception.GlobalExceptionHandler;
import com.user.service.UserService;
import com.user.util.Constant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.doNothing;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

/**
 * Unit tests for the {@link UserController} class.
 */
class UserControllerTest {

  /**
   * Mocked {@link UserService} instance used for simulating interactions with the service layer.
   */
  @Mock
  private UserService userService;

  /**
   * {@link UserController} instance with injected mocks for testing.
   */
  @InjectMocks
  private UserController userController;

  /**
   * Example {@link UserOutDto} used in tests.
   */
  private UserOutDto userOutDto;

  /**
   * Example {@link UserInDto} used in tests.
   */
  private UserInDto userInDto;

  /**
   * Initializes test data and mocks before each test method.
   */
  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);

    userOutDto = new UserOutDto();
    userOutDto.setId(101); // Placeholder user ID
    userOutDto.setName("Test User"); // Placeholder name
    userOutDto.setEmail("testuser@example.com"); // Placeholder email
    userOutDto.setWalletBalance(1000.0); // Placeholder wallet balance
    userOutDto.setPhoneNo("1234567890"); // Placeholder phone number

    userInDto = new UserInDto();
    userInDto.setName("Test User"); // Placeholder name
    userInDto.setEmail("testuser@example.com"); // Placeholder email
    userInDto.setPhoneNo("1234567890"); // Placeholder phone number
    userInDto.setPassword("password123"); // Placeholder password
  }

  /**
   * Tests the {@link UserController(int)} method.
   * Validates that the correct user details are returned for a given user ID.
   */
  @Test
  void testGetUser() {
    when(userService.getUser(anyInt())).thenReturn(userOutDto);
    ResponseEntity<?> response = userController.getUser(101); // Placeholder user ID
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(userOutDto, response.getBody());
  }

  /**
   * Tests the {@link UserController#getAllUser()} method.
   * Validates that a list of all users is returned.
   */
  @Test
  void testGetAllUser() {
    List<UserOutDto> userOutDtoList = Arrays.asList(userOutDto);
    when(userService.getAllUser()).thenReturn(userOutDtoList);
    ResponseEntity<?> response = userController.getAllUser();
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(userOutDtoList, response.getBody());
  }

  /**
   * Tests the {@link UserController#addUser(UserInDto)} method.
   * Validates that a user is created successfully and the correct response is returned.
   */
  @Test
  void testAddUser() {
    // Mock the service method
    doNothing().when(userService).addUser(any(UserInDto.class));

    ApiResponse expectedResponse = new ApiResponse("user created successfull"); // Placeholder message

    ResponseEntity<?> response = userController.addUser(userInDto);

    ApiResponse actualResponse = (ApiResponse) response.getBody();

    assertEquals(HttpStatus.CREATED, response.getStatusCode());
    assertEquals(expectedResponse.getMessage(), actualResponse.getMessage());
  }

  /**
   * Tests the {@link UserController(int, UserInDto)} method.
   * Validates that a user is updated successfully and the correct response is returned.
   */
  @Test
  void testUpdateUser() {
    doNothing().when(userService).updateUser(anyInt(), any(UserInDto.class));

    ApiResponse apiResponse = new ApiResponse(Constant.USER_UPDATED_SUCCESS); // Placeholder message

    ResponseEntity<?> response = userController.updateUser(101, userInDto); // Placeholder user ID

    ApiResponse actualResponse = (ApiResponse) response.getBody();

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(apiResponse.getMessage(), actualResponse.getMessage());
  }

  /**
   * Tests the {@link UserController#(int)} method.
   * Validates that the correct user details are returned after deletion for a given user ID.
   */
  @Test
  void testDeleteUser() {
    when(userService.deleteUser(anyInt())).thenReturn(userOutDto);
    ResponseEntity<?> response = userController.deleteUser(101); // Placeholder user ID
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(userOutDto, response.getBody());
  }

  /**
   * Tests the {@link UserController#loginUser(LoginInDto)} method.
   * Validates that the correct user details are returned upon successful login.
   */
  @Test
  void testLoginUser() {
    LoginInDto loginInDto = new LoginInDto();
    loginInDto.setEmail("testuser@example.com"); // Placeholder email
    loginInDto.setPassword("password123"); // Placeholder password
    when(userService.loginUser(any(LoginInDto.class))).thenReturn(userOutDto);

    ResponseEntity<?> response = userController.loginUser(loginInDto);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(userOutDto, response.getBody());
  }

  /**
   * Tests the {@link UserController#loginUser(LoginInDto)} method with invalid credentials.
   * Validates that an unauthorized response is returned for invalid login credentials.
   */
  @Test
  void testLoginUserWithInvalidCredentials() {
    LoginInDto loginInDto = new LoginInDto();
    loginInDto.setEmail("testuser@example.com"); // Placeholder email
    loginInDto.setPassword("wrongpassword"); // Placeholder incorrect password
    when(userService.loginUser(any(LoginInDto.class))).thenReturn(null);

    ResponseEntity<?> response = userController.loginUser(loginInDto);
    assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
    assertEquals("Invalid credentials", ((GlobalExceptionHandler.ErrorResponse)
      response.getBody()).getMessage()); // Placeholder message
  }

  /**
   * Tests the {@link UserController#(int, AmountInDto)} method.
   * Validates that the money is deducted successfully for a given user ID and amount.
   */
  @Test
  void testDeductMoney() {
    AmountInDto amountInDto = new AmountInDto();
    amountInDto.setMoney(100.0); // Placeholder amount

    ResponseEntity<?> response = userController.deductMoney(101, amountInDto); // Placeholder user ID
    assertEquals(HttpStatus.OK, response.getStatusCode());
  }

  /**
   * Tests the {@link UserController#(int, AmountInDto)} method.
   * Validates that the money is added successfully for a given user ID and amount.
   */
  @Test
  void testAddMoney() {
    AmountInDto amountInDto = new AmountInDto();
    amountInDto.setMoney(200.0); // Placeholder amount

    ResponseEntity<?> response = userController.addMoney(101, amountInDto); // Placeholder user ID
    assertEquals(HttpStatus.OK, response.getStatusCode());
  }
}
