package com.orders;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * The entry point of the Orders application.
 * <p>
 * This class contains the main method to launch the Spring Boot application.
 * It is annotated with {@link SpringBootApplication} to enable auto-configuration,
 * component scanning, and configuration in a Spring Boot application. It also
 * uses the {@link EnableFeignClients} annotation to enable Feign clients for
 * communication between microservices.
 * </p>
 */
@SpringBootApplication
@EnableFeignClients
public class OrdersApplication {
  /**
   * The main method to run the Orders application.
   * <p>
   * This method uses {@link SpringApplication#run(Class, String...)} to bootstrap
   * the Spring Boot application. It sets up the application context, performs
   * classpath scanning, and starts the embedded server.
   * </p>
   *
   * @param args command-line arguments passed to the application
   */
  public static void main(final String[] args) {
    SpringApplication.run(OrdersApplication.class, args);
  }
}

