package com.orders.service;

import com.orders.dto.AddressOutDto;
import com.orders.dto.AmountInDto;
import com.orders.dto.UserOutDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(name = "user-service", url = "http://localhost:8080")
public interface UserFClient {
  @GetMapping("/user/get/{id}")
  UserOutDto getUserById(@PathVariable("id") Integer userId);

  @PutMapping("/user/addMoney/{userId}")
  void addMoneyToUser(@PathVariable("userId") Integer userId, @RequestBody AmountInDto amountInDto);

  @PutMapping("/user/deduct/{userId}")
  void deductMoneyFromUser(@PathVariable("userId") Integer userId, @RequestBody AmountInDto amountInDto);

  @GetMapping("/get/{userId}")
  ResponseEntity<AddressOutDto> getAddressByUserId(@PathVariable Integer userId);

  @GetMapping("/address/getByAddress/{addressId}")
  ResponseEntity<AddressOutDto> getAddressByAddressId(@PathVariable Integer addressId);
}

