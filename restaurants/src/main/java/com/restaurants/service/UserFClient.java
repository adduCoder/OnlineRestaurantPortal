package com.restaurants.service;


import com.restaurants.dto.UserOutDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Feign client interface for interacting with the User Service.
 * This interface defines methods for communicating with the User Service using HTTP requests.
 */
@FeignClient(name = "user-service", url = "http://localhost:8080")
public interface UserFClient {

  /**
   * Fetches a user by their ID from the User Service.
   *
   * @param userId the ID of the user to fetch
   * @return the {@link UserOutDto} object containing the user's details
   */
  @GetMapping("/user/get/{id}")
  UserOutDto getUserById(@PathVariable("id") Integer userId);
}
