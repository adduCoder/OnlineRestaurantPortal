package com.user.service;

import com.user.dto.UserInDto;
import com.user.dto.UserOutDto;
import com.user.entity.User;
import com.user.exception.NotFound;
import com.user.exception.UserAlreadyExisted;
import com.user.repository.UserRepo;
import com.user.util.PasswordEncoder;
import com.user.util.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UserServiceTest {

  @Mock
  private UserRepo userRepo;

  @Mock
  private AddressService addressService;

  @InjectMocks
  private UserService userService;

  private User user;
  private UserOutDto userOutDto;
  private UserInDto userInDto;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);

    user = new User();
    user.setId(1);
    user.setName("Test User");
    user.setEmail("test@gmail.com");
    user.setPhoneNo("9876543210");
    user.setPassword(PasswordEncoder.encodePassword("0123"));
    user.setRole(Role.USER);

    userOutDto = new UserOutDto();
    userOutDto.setId(1);
    userOutDto.setName("Test User");
    userOutDto.setEmail("test@gmail.com");
    userOutDto.setWalletBalance(1000.0);
    userOutDto.setPhoneNo("9876543210");

    userInDto = new UserInDto();
    userInDto.setName("Test User");
    userInDto.setEmail("test@gmail.com");
    userInDto.setPassword("0123");
    userInDto.setPhoneNo("9876543210");
    userInDto.setRole(Role.USER);
  }

  @Test
  public void addUserSuccessTest() {
    when(userRepo.findByEmail("test@gmail.com")).thenReturn(Optional.empty());
    when(userRepo.save(any(User.class))).thenAnswer(invocation -> {
      User user = invocation.getArgument(0);
      user.setId(1);
      return user;
    });

    userService.addUser(userInDto);

    verify(userRepo, times(1)).findByEmail("test@gmail.com");
    verify(userRepo, times(1)).save(any(User.class));
  }

  @Test
  public void addUserAlreadyExistsTest() {
    when(userRepo.findByEmail("test@gmail.com")).thenReturn(Optional.of(user));
    assertThrows(UserAlreadyExisted.class, () -> userService.addUser(userInDto));
  }

  @Test
  public void getUserSuccessTest() {
    when(userRepo.findById(1)).thenReturn(Optional.of(user));
    UserOutDto result = userService.getUser(1);
    assertEquals("Test User", result.getName());
    assertEquals("test@gmail.com", result.getEmail());
    verify(userRepo, times(1)).findById(1);
  }

  @Test
  public void getUserNotFoundTest() {
    when(userRepo.findById(1)).thenReturn(Optional.empty());
    assertThrows(NotFound.class, () -> userService.getUser(1));
  }

  @Test
  public void getAllUserTest() {
    User user2 = new User();
    user2.setId(2);
    user2.setName("Bharat");
    user2.setEmail("bharat@gmail.com");
    user2.setPhoneNo("9876543211");
    user2.setPassword(PasswordEncoder.encodePassword("password123"));
    user2.setRole(Role.USER);

    List<User> userList = Arrays.asList(user, user2);
    when(userRepo.findAll()).thenReturn(userList);
    List<UserOutDto> result = userService.getAllUser();
    assertEquals(2, result.size());
    assertEquals("Test User", result.get(0).getName());
    assertEquals("Bharat", result.get(1).getName());
    verify(userRepo, times(1)).findAll();
  }

  @Test
  public void updateUserSuccessTest() {
    UserInDto updatedUserInDto = new UserInDto();
    updatedUserInDto.setName("Test User Updated");
    updatedUserInDto.setEmail("Test User_updated@gmail.com");
    updatedUserInDto.setPassword("updatedPassword");
    updatedUserInDto.setPhoneNo("9876543210");
    updatedUserInDto.setRole(Role.USER);

    when(userRepo.findById(1)).thenReturn(Optional.of(user));
    when(userRepo.save(any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));

    userService.updateUser(1, updatedUserInDto);

    verify(userRepo, times(1)).findById(1);
    verify(userRepo, times(1)).save(any(User.class));
  }

  @Test
  public void updateUserNotFoundTest() {
    UserInDto userInDto = new UserInDto();
    when(userRepo.findById(1)).thenReturn(Optional.empty());
    assertThrows(NotFound.class, () -> userService.updateUser(1, userInDto));
  }

  @Test
  public void deleteUserSuccessTest() {
    when(userRepo.findById(1)).thenReturn(Optional.of(user));
    doNothing().when(userRepo).delete(user);

    UserOutDto result = userService.deleteUser(1);

    assertNotNull(result);
    assertEquals("Test User", result.getName());

    verify(userRepo).findById(1);
    verify(userRepo).delete(user);
  }

  @Test
  public void deleteUserNotFoundTest() {
    when(userRepo.findById(1)).thenReturn(Optional.empty());
    assertThrows(NotFound.class, () -> userService.deleteUser(1));
  }
}
