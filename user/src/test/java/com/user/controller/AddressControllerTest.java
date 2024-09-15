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

class AddressControllerTest {

  @Mock
  private AddressService addressService;

  @InjectMocks
  private AddressController addressController;

  private AddressOutDto addressResponse;
  private AddressInDto addressInDto;
  private AddressInDto updateAddressInDto;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);

    addressResponse = new AddressOutDto();
    addressResponse.setUserId(1);
    addressResponse.setStreet("MG Road");
    addressResponse.setCity("Bangalore");
    addressResponse.setState("Karnataka");
    addressResponse.setPinCode(560001);

    addressInDto = new AddressInDto();
    addressInDto.setUserId(1);
    addressInDto.setStreet("MG Road");
    addressInDto.setCity("Bangalore");
    addressInDto.setState("Karnataka");
    addressInDto.setPinCode(560001);

    updateAddressInDto = new AddressInDto();
    updateAddressInDto.setStreet("Brigade Road");
    updateAddressInDto.setCity("Mumbai");
    updateAddressInDto.setState("Maharashtra");
    updateAddressInDto.setPinCode(400001);
  }

  @Test
  void testAddAddress() {
    AddressOutResponse addressOutResponse = new AddressOutResponse();
    addressOutResponse.setMessage("address added successfull"); // Update this line

    ResponseEntity<?> expectedResponse = new ResponseEntity<>(addressOutResponse, HttpStatus.CREATED);

    ResponseEntity<?> response = addressController.addAddress(addressInDto);
    assertEquals(HttpStatus.CREATED, response.getStatusCode());
    assertEquals("address added successfull", ((AddressOutResponse) response.getBody()).getMessage()); // Update this line
  }


  @Test
  void testGetAddressByUserId() {
    List<AddressOutDto> addressResponseList = Arrays.asList(addressResponse);
    when(addressService.getAddressByUserId(anyInt())).thenReturn(addressResponseList);
    ResponseEntity<?> response = addressController.getAddressByUserId(1);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(addressResponseList, response.getBody());
  }

  @Test
  void testDeleteAddress() {
    when(addressService.deleteAddress(anyInt())).thenReturn("Address deleted successfull");
    ResponseEntity<?> response = addressController.deleteAddress(1);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals("Address deleted successfull", response.getBody());
  }

  @Test
  void testUpdateAddress() {
    AddressOutResponse addressOutResponse = new AddressOutResponse();
    addressOutResponse.setMessage("address updated successfull");

    ResponseEntity<?> expectedResponse = new ResponseEntity<>(addressOutResponse, HttpStatus.OK);

    ResponseEntity<?> response = addressController.updateAddress(1, updateAddressInDto);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals("address updated successfull", ((AddressOutResponse) response.getBody()).getMessage());
  }

}
