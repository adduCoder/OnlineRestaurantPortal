package com.restaurants.service;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.restaurants.dto.outdto.UserOutDto;

@FeignClient(name = "user-service", url = "http://localhost:8080")
public interface UserFClient {
  @GetMapping("/user/get/{id}")
  UserOutDto getUserById(@PathVariable("id") Integer userId);
}

