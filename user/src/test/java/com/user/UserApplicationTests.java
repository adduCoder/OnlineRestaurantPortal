package com.user;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.springframework.context.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Test class for {@link UserApplication}.
 * <p>
 * This class contains test cases that verify the correct behavior of the {@link UserApplication} class,
 * specifically focusing on the loading of the Spring ApplicationContext.
 * </p>
 */
@SpringBootTest
@ActiveProfiles("test")  // Optional: If you have a specific test profile, use this annotation to activate it.
public class UserApplicationTests {

  @Autowired
  private ApplicationContext applicationContext;

  /**
   * Test case to verify that the Spring ApplicationContext loads correctly.
   * <p>
   * This test checks that the application context is not null, indicating that the application has started up properly.
   * </p>
   */
  @Test
  void contextLoads() {
    assertNotNull(applicationContext, "The application context should have loaded.");
  }

  /**
   * Test case to verify that the main method in {@link UserApplication} runs without exceptions.
   * <p>
   * This test calls the main method of the application and ensures that no exceptions are thrown during startup.
   * </p>
   */
  @Test
  void testMain() {
    UserApplication.main(new String[] {});
  }
}
