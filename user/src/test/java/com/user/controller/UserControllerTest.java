package com.user.controller;

import com.user.dto.AmountInDto;
import com.user.dto.LoginInDto;
import com.user.dto.UserInDto;
import com.user.dto.UserOutDto;
import com.user.exceptionhandler.GlobalExceptionHandler;
import com.user.service.UserService;
import com.user.util.UserApiResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

class UserControllerTest {

  @Mock
  private UserService userService;

  @InjectMocks
  private UserController userController;

  private UserOutDto userOutDto;
  private UserInDto userInDto;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);

    userOutDto = new UserOutDto();
    userOutDto.setId(1);
    userOutDto.setName("Arjun");
    userOutDto.setEmail("arjun@gmail.com");
    userOutDto.setWalletBalance(1000.0);
    userOutDto.setPhoneNo("9876543210");

    userInDto = new UserInDto();
    userInDto.setName("Arjun");
    userInDto.setEmail("arjun@gmail.com");
    userInDto.setPhoneNo("9876543210");
    userInDto.setPassword("0123");
  }

  @Test
  void testGetUser() {
    when(userService.getUser(anyInt())).thenReturn(userOutDto);
    ResponseEntity<?> response = userController.getUser(1);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(userOutDto, response.getBody());
  }

  @Test
  void testGetAllUser() {
    List<UserOutDto> userOutDtoList = Arrays.asList(userOutDto);
    when(userService.getAllUser()).thenReturn(userOutDtoList);
    ResponseEntity<?> response = userController.getAllUser();
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(userOutDtoList, response.getBody());
  }

  @Test
  void testAddUser() {
    when(userService.addUser(any(UserInDto.class))).thenReturn(1); // Assuming service returns user ID

    UserApiResponse userApiResponse = new UserApiResponse();
    userApiResponse.setMessage("New User Registerd Successfully");
    userApiResponse.setUserId(1);
    userApiResponse.setRole(userInDto.getRole());

    ResponseEntity<?> response = userController.addUser(userInDto);
    assertEquals(HttpStatus.CREATED, response.getStatusCode());
    assertEquals(userApiResponse.getMessage(), ((UserApiResponse) response.getBody()).getMessage());
    assertEquals(userApiResponse.getUserId(), ((UserApiResponse) response.getBody()).getUserId());
    assertEquals(userApiResponse.getRole(), ((UserApiResponse) response.getBody()).getRole());
  }

  @Test
  void testUpdateUser() {
    when(userService.updateUser(anyInt(), any(UserInDto.class))).thenReturn(userOutDto);

    UserApiResponse userApiResponse = new UserApiResponse();
    userApiResponse.setMessage("User Updated Successfully");
    userApiResponse.setUserId(1);

    ResponseEntity<?> response = userController.updateUser(1, userInDto);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(userApiResponse.getMessage(), ((UserApiResponse) response.getBody()).getMessage());
    assertEquals(userApiResponse.getUserId(), ((UserApiResponse) response.getBody()).getUserId());
  }

  @Test
  void testDeleteUser() {
    when(userService.deleteUser(anyInt())).thenReturn(userOutDto);
    ResponseEntity<?> response = userController.deleteUser(1);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(userOutDto, response.getBody());
  }

  @Test
  void testLoginUser() {
    LoginInDto loginInDto = new LoginInDto();
    loginInDto.setEmail("arjun@gmail.com");
    loginInDto.setPassword("0123");
    when(userService.loginUser(any(LoginInDto.class))).thenReturn(userOutDto);

    ResponseEntity<?> response = userController.loginUser(loginInDto);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(userOutDto, response.getBody());
  }

  @Test
  void testLoginUserWithInvalidCredentials() {
    LoginInDto loginInDto = new LoginInDto();
    loginInDto.setEmail("arjun@gmail.com");
    loginInDto.setPassword("wrongpassword");
    when(userService.loginUser(any(LoginInDto.class))).thenReturn(null);

    ResponseEntity<?> response = userController.loginUser(loginInDto);
    assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
    assertEquals("Invalid credentials", ((GlobalExceptionHandler.ErrorResponse) response.getBody()).getMessage());
  }

  @Test
  void testDeductMoney() {
    AmountInDto amountInDto = new AmountInDto();
    amountInDto.setMoney(100.0);

    ResponseEntity<?> response = userController.deductMoney(1, amountInDto);
    assertEquals(HttpStatus.OK, response.getStatusCode());
  }

  @Test
  void testAddMoney() {
    AmountInDto amountInDto = new AmountInDto();
    amountInDto.setMoney(200.0);

    ResponseEntity<?> response = userController.addMoney(1, amountInDto);
    assertEquals(HttpStatus.OK, response.getStatusCode());
  }
}
