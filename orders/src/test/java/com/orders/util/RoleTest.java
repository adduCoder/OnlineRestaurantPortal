package com.orders.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

public class RoleTest {

  @Test
  public void testEnumValues() {
    // Check that the enum contains the expected values
    assertEquals("USER", Role.USER.name());
    assertEquals("OWNER", Role.OWNER.name());
  }

  @Test
  public void testEnumLength() {
    // Check the number of constants in the enum
    assertEquals(2, Role.values().length);
  }

  @Test
  public void testEnumValueOf() {
    // Check that enum values can be retrieved correctly
    assertSame(Role.valueOf("USER"), Role.USER);
    assertSame(Role.valueOf("OWNER"), Role.OWNER);
  }
}
