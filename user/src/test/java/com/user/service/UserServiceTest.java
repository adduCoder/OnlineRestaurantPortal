package com.user.service;

import com.user.dto.AmountInDto;
import com.user.dto.LoginInDto;
import com.user.dto.UserInDto;
import com.user.dto.UserOutDto;
import com.user.entity.User;
import com.user.exception.InvalidPasswordException;
import com.user.exception.NotFoundException;
import com.user.exception.UserAlreadyExisted;
import com.user.repository.UserRepository;
import com.user.util.PasswordEncoder;
import com.user.util.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceTest {

  @Mock
  private UserRepository userRepository;

  @Mock
  private AddressService addressService;

  @InjectMocks
  private UserService userService;

  private User user;
  private UserInDto userInDto;
  private UserOutDto userOutDto;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);

    // Set up dummy user and DTOs for testing
    user = new User();
    user.setId(1);
    user.setName("Aarav");
    user.setEmail("aarav@gmail.com");
    user.setPassword(PasswordEncoder.encodePassword("Test@123"));
    user.setPhoneNo("9876543210");
    user.setRole(Role.USER);
    user.setWalletBalance(1000.0);

    userInDto = new UserInDto();
    userInDto.setName("Aarav");
    userInDto.setEmail("aarav@gmail.com");
    userInDto.setPassword("Test@123");
    userInDto.setPhoneNo("9876543210");
    userInDto.setRole(Role.USER);

    userOutDto = new UserOutDto();
    userOutDto.setId(1);
    userOutDto.setName("Aarav");
    userOutDto.setEmail("aarav@gmail.com");
    userOutDto.setPhoneNo("9876543210");
    userOutDto.setWalletBalance(1000.0);
  }

  @Test
  public void testAddUser_Success() {
    when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.empty());
    when(userRepository.save(any(User.class))).thenReturn(user);

    userService.addUser(userInDto);

    verify(userRepository, times(1)).save(any(User.class));
  }

  @Test
  public void testAddUser_UserAlreadyExists() {
    when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));

    assertThrows(UserAlreadyExisted.class, () -> userService.addUser(userInDto));
  }

  @Test
  public void testGetUser_NotFound() {
    when(userRepository.findById(user.getId())).thenReturn(Optional.empty());

    assertThrows(NotFoundException.class, () -> userService.getUser(user.getId()));
  }

  @Test
  public void testDeleteUser_Success() {
    when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
    when(addressService.getAddressByUserId(user.getId())).thenReturn(Arrays.asList());

    userService.deleteUser(user.getId());

    verify(userRepository, times(1)).delete(user);
  }

  @Test
  public void testDeleteUser_NotFound() {
    when(userRepository.findById(user.getId())).thenReturn(Optional.empty());

    assertThrows(NotFoundException.class, () -> userService.deleteUser(user.getId()));
  }

  @Test
  public void testAddMoney_Success() {
    AmountInDto amountInDto = new AmountInDto();
    amountInDto.setMoney(100.0);
    when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));

    userService.addMoney(user.getId(), amountInDto);

    assertEquals(1100.0, user.getWalletBalance());
  }

  @Test
  public void testDeductMoney_Success() {
    AmountInDto amountInDto = new AmountInDto();
    amountInDto.setMoney(200.0);
    when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));

    userService.deductMoney(user.getId(), amountInDto);

    assertEquals(800.0, user.getWalletBalance());
  }

  @Test
  public void testLoginUser_Success() {
    LoginInDto loginInDto = new LoginInDto("aarav@gmail.com", "Test@123");
    when(userRepository.findByEmail("aarav@gmail.com")).thenReturn(Optional.of(user));

    UserOutDto result = userService.loginUser(loginInDto);

    assertNotNull(result);
    assertEquals(user.getEmail(), result.getEmail());
  }

  @Test
  public void testLoginUser_IncorrectPassword() {
    LoginInDto loginInDto = new LoginInDto("aarav@gmail.com", "WrongPassword");
    when(userRepository.findByEmail("aarav@gmail.com")).thenReturn(Optional.of(user));

    UserOutDto result = userService.loginUser(loginInDto);

    assertNull(result);
  }

  @Test
  public void testLoginUser_UserNotFound() {
    LoginInDto loginInDto = new LoginInDto("nonexistent@gmail.com", "Test@123");
    when(userRepository.findByEmail("nonexistent@gmail.com")).thenReturn(Optional.empty());

    UserOutDto result = userService.loginUser(loginInDto);

    assertNull(result);
  }
}
