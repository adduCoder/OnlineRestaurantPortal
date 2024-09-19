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

/**
 * Feign client for interacting with the user service.
 * This client communicates with the user service to perform operations related
 * to users and their addresses.
 */
@FeignClient(name = "user-service", url = "http://localhost:8080")
public interface UserFClient {
  /**
   * Retrieves user details based on the provided user ID.
   *
   * @param userId the ID of the user to retrieve
   * @return the UserOutDto containing the details of the user
   */
  @GetMapping("/user/get/{id}")
  UserOutDto getUserById(@PathVariable("id") Integer userId);

  /**
   * Adds money to a user's account based on the provided user ID and amount.
   *
   * @param userId the ID of the user to whom money will be added
   * @param amountInDto the DTO containing the amount to be added
   */
  @PutMapping("/user/addMoney/{userId}")
  void addMoneyToUser(@PathVariable("userId") Integer userId, @RequestBody AmountInDto amountInDto);

  /**
   * Deducts money from a user's account based on the provided user ID and amount.
   *
   * @param userId the ID of the user from whom money will be deducted
   * @param amountInDto the DTO containing the amount to be deducted
   */
  @PutMapping("/user/deduct/{userId}")
  void deductMoneyFromUser(@PathVariable("userId") Integer userId, @RequestBody AmountInDto amountInDto);

  /**
   * Retrieves the address associated with a user based on the provided user ID.
   *
   * @param userId the ID of the user whose address is to be retrieved
   * @return a ResponseEntity containing the address details of the user
   */
  @GetMapping("/get/{userId}")
  ResponseEntity<AddressOutDto> getAddressByUserId(@PathVariable Integer userId);

  /**
   * Retrieves the address details based on the provided address ID.
   *
   * @param addressId the ID of the address to retrieve
   * @return a ResponseEntity containing the address details
   */
  @GetMapping("/address/getByAddress/{addressId}")
  ResponseEntity<AddressOutDto> getAddressByAddressId(@PathVariable Integer addressId);
}

