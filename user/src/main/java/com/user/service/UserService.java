package com.user.service;

import com.user.dtoconversion.DtoConversion;
import com.user.entity.User;
import com.user.exceptionhandler.NoCustomerFound;
import com.user.exceptionhandler.UserAlreadyExisted;
import com.user.indto.LoginInDto;
import com.user.indto.UserInDto;
import com.user.outdto.UserOutDto;
import com.user.repository.UserRepo;
import com.user.util.PasswordEncoder;
import com.user.util.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

  @Autowired
  private UserRepo userRepo;

  public UserOutDto getUser(Integer userId) {
    Optional<User> optionalUser = userRepo.findById(userId);
    if (optionalUser.isPresent() == false) {
      throw new NoCustomerFound();
    }
    User user = optionalUser.get();
    return DtoConversion.userToResponse(user);
  }

  public List<UserOutDto> getAllUser() {
    List<User> userList = userRepo.findAll();
    List<UserOutDto> userOutDtoList = new ArrayList<>();
    for (User user : userList) {
      userOutDtoList.add(DtoConversion.userToResponse(user));
    }
    return userOutDtoList;
  }

  public Integer addUser(UserInDto userInDto) {
    User newUser = DtoConversion.requestToUser(userInDto);
    String encodedPassword = PasswordEncoder.encodePassword(newUser.getPassword());
    newUser.setPassword(encodedPassword);
    Optional<User> existedUser = userRepo.findByEmail(newUser.getEmail());
    if (existedUser.isPresent()) {
      throw new UserAlreadyExisted();
    }
    if (newUser.getRole().equals(Role.OWNER)) newUser.setWalletBalance(null);
    userRepo.save(newUser);
    return newUser.getId();
  }

  public UserOutDto updateUser(Integer userId, UserInDto userInDto) {
    Optional<User> optionalUser = userRepo.findById(userId);
    if (optionalUser.isPresent() == false) {
      throw new NoCustomerFound();
    }
    User user = optionalUser.get();
    user.setName(userInDto.getName());
    user.setEmail(userInDto.getEmail());
    user.setPhoneNo(userInDto.getPhoneNo());
    userRepo.save(user);
    return DtoConversion.userToResponse(user);
  }


  public UserOutDto deleteUser(Integer userId) {
    Optional<User> optionalUser = userRepo.findById(userId);
    if (optionalUser.isPresent() == false) {
      throw new NoCustomerFound();
    }
    User user = optionalUser.get();
    userRepo.delete(user);
    return DtoConversion.userToResponse(user);
  }

  public String loginUser(LoginInDto loginInDto) {
    String email = loginInDto.getEmail();
    String password = loginInDto.getPassword();
    Optional<User> existedUser = userRepo.findByEmail(email);
    if (existedUser.isPresent() == false) {
      return "Email Not Found";
    }
    User user = existedUser.get();
    String existedPassword = PasswordEncoder.decodePassword(user.getPassword());
    if (existedPassword.equals(password)) return "Login Success";
    return "Password Mismatched";
  }
}

