package com.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The entry point for the User application.
 * <p>
 * This is a Spring Boot application class that contains the {@link #main(String[])} method,
 * which is used to launch the application. The class is annotated with {@link SpringBootApplication},
 * which enables auto-configuration, component scanning, and configuration.
 * </p>
 */
@SpringBootApplication
public class UserApplication {
  /**
   * The main method that serves as the entry point for the application.
   * <p>
   * This method uses the {@link SpringApplication#run(Class, String[])} method to launch
   * the Spring Boot application. It initializes the Spring context and starts the embedded server.
   * </p>
   *
   * @param args command-line arguments passed to the application
   */
  public static void main(String[] args) {
    SpringApplication.run(UserApplication.class, args);
  }
}
