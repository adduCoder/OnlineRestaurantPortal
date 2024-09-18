package com.user.controller;

import com.user.dto.AddressInDto;
import com.user.dto.AddressOutDto;
import com.user.service.AddressService;
import com.user.util.AddressOutResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

/**
 * Unit tests for the {@link AddressController} class.
 */
class AddressControllerTest {

  /**
   * Mocked {@link AddressService} instance used for simulating interactions with the service layer.
   */
  @Mock
  private AddressService addressService;

  /**
   * {@link AddressController} instance with injected mocks for testing.
   */
  @InjectMocks
  private AddressController addressController;

  /**
   * Example {@link AddressOutDto} used in tests.
   */
  private AddressOutDto addressResponse;

  /**
   * Example {@link AddressInDto} used in tests for adding a new address.
   */
  private AddressInDto addressInDto;

  /**
   * Example {@link AddressInDto} used in tests for updating an address.
   */
  private AddressInDto updateAddressInDto;

  /**
   * Initializes test data and mocks before each test method.
   */
  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);

    addressResponse = new AddressOutDto();
    addressResponse.setUserId(123);
    addressResponse.setStreet("123 Placeholder St");
    addressResponse.setCity("Test City");
    addressResponse.setState("Test State");
    addressResponse.setPinCode(123456);

    addressInDto = new AddressInDto();
    addressInDto.setUserId(123);
    addressInDto.setStreet("123 Placeholder St");
    addressInDto.setCity("Test City");
    addressInDto.setState("Test State");
    addressInDto.setPinCode(123456);

    updateAddressInDto = new AddressInDto();
    updateAddressInDto.setStreet("456 Updated Rd");
    updateAddressInDto.setCity("Updated City");
    updateAddressInDto.setState("Updated State");
    updateAddressInDto.setPinCode(654321);
  }

  /**
   * Tests the {@link AddressController#addAddress(AddressInDto)} method.
   * Validates that the address is added successfully and the correct response is returned.
   */
  @Test
  void testAddAddress() {
    AddressOutResponse addressOutResponse = new AddressOutResponse();
    addressOutResponse.setMessage("Address added successfully");

    ResponseEntity<?> expectedResponse = new ResponseEntity<>(addressOutResponse, HttpStatus.CREATED);

    ResponseEntity<?> response = addressController.addAddress(addressInDto);
    assertEquals(HttpStatus.CREATED, response.getStatusCode());
    assertEquals("address added successfull", ((AddressOutResponse) response.getBody()).getMessage());
  }

  /**
   * Validates that the correct address details are returned for a given user ID.
   */
  @Test
  void testGetAddressByUserId() {
    List<AddressOutDto> addressResponseList = Arrays.asList(addressResponse);
    when(addressService.getAddressByUserId(anyInt())).thenReturn(addressResponseList);
    ResponseEntity<?> response = addressController.getAddressByUserId(123); // Placeholder user ID
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(addressResponseList, response.getBody());
  }

  /**
   * Validates that the address is deleted successfully and the correct response is returned.
   */
  @Test
  void testDeleteAddress() {
    when(addressService.deleteAddress(anyInt())).thenReturn("Address deleted successfully");
    ResponseEntity<?> response = addressController.deleteAddress(123);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals("Address deleted successfully", response.getBody());
  }

  /**
   * Validates that the address is updated successfully and the correct response is returned.
   */
  @Test
  void testUpdateAddress() {
    AddressOutResponse addressOutResponse = new AddressOutResponse();
    addressOutResponse.setMessage("Address updated successfully"); // Placeholder message

    ResponseEntity<?> expectedResponse = new ResponseEntity<>(addressOutResponse, HttpStatus.OK);

    ResponseEntity<?> response = addressController.updateAddress(123, updateAddressInDto); // Placeholder user ID
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals("address updated successfull", ((AddressOutResponse) response.getBody()).getMessage()); // Placeholder message
  }
}
