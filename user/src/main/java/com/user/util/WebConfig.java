package com.user.util;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuration class for setting up Cross-Origin Resource Sharing (CORS) in the application.
 * <p>
 * This configuration allows the frontend application running on http://localhost:3000 to make requests
 * to the backend, with support for various HTTP methods and headers.
 * </p>
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
  /**
   * Configures CORS mappings for the application.
   * <p>
   * This method allows all endpoints to be accessed from the specified origin,
   * and permits the specified HTTP methods and headers.
   * </p>
   *
   * @param registry the CORS registry to be configured
   */
  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**")  // Allow all endpoints
      .allowedOrigins("*")  // Allow this origin
      .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")  // Allow these methods
      .allowedHeaders("*");  // Allow all headers
  }
}
