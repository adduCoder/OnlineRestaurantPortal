package com.user.controller;

import com.user.indto.LoginInDto;
import com.user.indto.UserInDto;
import com.user.outdto.UserOutDto;
import com.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

  @Autowired
  private UserService userService;

  @GetMapping("/get/{userId}")
  public ResponseEntity<?> getUser(@PathVariable Integer userId) {
    UserOutDto userOutDto = userService.getUser(userId);
    return new ResponseEntity<>(userOutDto, HttpStatus.OK);
  }

  @GetMapping("/getAll")
  public ResponseEntity<?> getAllUser() {
    List<UserOutDto> allUserOutDto = userService.getAllUser();
    return new ResponseEntity<>(allUserOutDto, HttpStatus.OK);
  }

  @PostMapping("/add")
  public ResponseEntity<?> addUser(@Valid @RequestBody UserInDto userInDto) {
    Integer createdUser = userService.addUser(userInDto);
    if (createdUser == null) return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
  }


  @PutMapping("/update/{userId}")
  public ResponseEntity<?> updateUser(@PathVariable Integer userId, @Valid @RequestBody UserInDto userInDto) {
    UserOutDto userOutDto = userService.updateUser(userId, userInDto);
    return new ResponseEntity<>(userOutDto, HttpStatus.OK);
  }

  @DeleteMapping("/delete/{userId}")
  public ResponseEntity<?> deleteUser(@PathVariable Integer userId) {
    UserOutDto userOutDto = userService.deleteUser(userId);
    return new ResponseEntity<>(userOutDto, HttpStatus.OK);
  }

  @GetMapping("/login")
  public ResponseEntity<?> loginUser(@Valid @RequestBody LoginInDto loginInDto) {
    String res = userService.loginUser(loginInDto);
    return new ResponseEntity<>(res, HttpStatus.OK);
  }

}


