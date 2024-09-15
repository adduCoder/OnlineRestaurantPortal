package com.user;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest
public class UserApplicationTests {

  @Autowired
  private ApplicationContext applicationContext;


  @Test
  void contextLoads() {
    assertNotNull(applicationContext, "The application context should have loaded.");
  }
//
//  @Test
//  void testMain() {
//     try {
//      UserApplication.main(new String[] {});
//    } catch (Exception e) {
//       throw new RuntimeException("Failed to start the application", e);
//    }
//  }
}
