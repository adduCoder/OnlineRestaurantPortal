package com.user.controller;

import com.user.indto.LoginInDto;
import com.user.indto.UserInDto;
import com.user.outdto.UserOutDto;
import com.user.service.UserService;
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
    when(userService.addUser(any(UserInDto.class))).thenReturn(1);
    ResponseEntity<?> response = userController.addUser(userInDto);
    assertEquals(HttpStatus.CREATED, response.getStatusCode());
    assertEquals(1, response.getBody());
  }

  @Test
  void testUpdateUser() {
    when(userService.updateUser(anyInt(), any(UserInDto.class))).thenReturn(userOutDto);
    ResponseEntity<?> response = userController.updateUser(1, userInDto);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(userOutDto, response.getBody());
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
    when(userService.loginUser(any(LoginInDto.class))).thenReturn("Login Success");
    LoginInDto loginInDto = new LoginInDto();
    loginInDto.setEmail("arjun@gmail.com");
    loginInDto.setPassword("password123");
    ResponseEntity<?> response = userController.loginUser(loginInDto);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals("Login Success", response.getBody());
  }
}
