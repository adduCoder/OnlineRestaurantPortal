package com.user.outdto;

import com.user.dto.AddressOutDto;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for {@link AddressOutDto}.
 * <p>
 * This class contains test cases to verify the correct behavior of the {@link AddressOutDto} class,
 * specifically focusing on getters, setters, and the correctness of its data.
 * </p>
 */
public class AddressOutDtoTest {

  @Test
  void testAddressOutDto() {
    // Create an instance of AddressOutDto
    AddressOutDto address = new AddressOutDto();

    // Set values
    address.setAddressId(1);
    address.setUserId(101);
    address.setStreet("Tower Square");
    address.setCity("Indore");
    address.setState("MP");
    address.setPinCode(452001);

    // Assert that the values are set and retrieved correctly
    assertEquals(1, address.getAddressId(), "Address ID should be 1");
    assertEquals(101, address.getUserId(), "User ID should be 101");
    assertEquals("Tower Square", address.getStreet(), "Street should be 'Tower Square'");
    assertEquals("Indore", address.getCity(), "City should be 'Indore'");
    assertEquals("MP", address.getState(), "State should be 'MP'");
    assertEquals(452001, address.getPinCode(), "PIN code should be 452001");

    // Test toString() method
    String expectedString = "AddressOutDto(addressId=1, userId=101, street=Tower Square, city=Indore, state=MP, pinCode=452001)";
    assertEquals(expectedString, address.toString(), "toString() should return the expected string");

    // Test equals() and hashCode() methods
    AddressOutDto anotherAddress = new AddressOutDto();
    anotherAddress.setAddressId(1);
    anotherAddress.setUserId(101);
    anotherAddress.setStreet("Tower Square");
    anotherAddress.setCity("Indore");
    anotherAddress.setState("MP");
    anotherAddress.setPinCode(452001);

    assertEquals(address, anotherAddress, "Two AddressOutDto objects with the same values should be equal");
    assertEquals(address.hashCode(), anotherAddress.hashCode(),
      "Hash codes should be equal for two AddressOutDto objects with the same values");
  }
}
