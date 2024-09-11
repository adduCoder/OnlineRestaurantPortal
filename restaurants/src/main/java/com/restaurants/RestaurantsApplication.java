package com.restaurants;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@Slf4j
@SpringBootApplication
@EnableFeignClients
public class RestaurantsApplication {

  /**
   * The main method to start the Spring Boot application.
   * @param args Command-line arguments.
   */
  public static void main(String[] args) {
    log.info("Starting RestaurantsApplication...");
    SpringApplication.run(RestaurantsApplication.class, args);
    log.info("RestaurantsApplication started successfully.");
  }
}
