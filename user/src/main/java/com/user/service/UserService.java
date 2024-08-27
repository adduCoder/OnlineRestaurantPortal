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
@Slf4j
@Service
public class UserService {

  @Autowired
  private UserRepo userRepo;

  @Autowired
  private AddressService addressService;

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

  public Integer addUser(UserInDto userInDto) {
    log.info("Adding new user with email: {}", userInDto.getEmail());
    User newUser = DtoConversion.requestToUser(userInDto);
    String encodedPassword = PasswordEncoder.encodePassword(newUser.getPassword());
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

  public String loginUser(LoginInDto loginInDto) {
    log.info("Attempting login for user with email: {}", loginInDto.getEmail());
    String email = loginInDto.getEmail();
    String password = loginInDto.getPassword();
    Optional<User> existedUser = userRepo.findByEmail(email);
    if (!existedUser.isPresent()) {
      log.warn("Email not found: {}", loginInDto.getEmail());
      return "Email Not Found";
    }
    User user = existedUser.get();
    String existedPassword = PasswordEncoder.decodePassword(user.getPassword());
    if (existedPassword.equals(password)) {
      log.info("Login successful for user with email: {}", loginInDto.getEmail());
      return "Login Success";
    }
    log.warn("Password mismatched for user with email: {}", loginInDto.getEmail());
    return "Password Mismatched";
  }
}

