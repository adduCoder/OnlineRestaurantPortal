package com.user.controller;

import com.user.indto.AddressRequest;
import com.user.indto.UpdateAddressRequest;
import com.user.outdto.AddressResponse;
import com.user.service.AddressService;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

class AddressControllerTest {

  @Mock
  private AddressService addressService;

  @InjectMocks
  private AddressController addressController;

  private AddressResponse addressResponse;
  private AddressRequest addressRequest;
  private UpdateAddressRequest updateAddressRequest;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);

    addressResponse = new AddressResponse();
    addressResponse.setUserId(1);
    addressResponse.setStreet("MG Road");
    addressResponse.setCity("Bangalore");
    addressResponse.setState("Karnataka");
    addressResponse.setPinCode(560001);

    addressRequest = new AddressRequest();
    addressRequest.setUserId(1);
    addressRequest.setStreet("MG Road");
    addressRequest.setCity("Bangalore");
    addressRequest.setState("Karnataka");
    addressRequest.setPinCode(560001);

    updateAddressRequest = new UpdateAddressRequest();
    updateAddressRequest.setStreet("Brigade Road");
    updateAddressRequest.setCity("Mumbai");
    updateAddressRequest.setState("Maharashtra");
    updateAddressRequest.setPinCode(400001);
  }

  @Test
  void testAddAddress() {
    when(addressService.createAddress(any(AddressRequest.class))).thenReturn(addressResponse);
    ResponseEntity<?> response = addressController.addAddress(addressRequest);
    assertEquals(HttpStatus.CREATED, response.getStatusCode());
    assertEquals(addressResponse, response.getBody());
  }

  @Test
  void testGetAddressByUserId() {
    List<AddressResponse> addressResponseList = Arrays.asList(addressResponse);
    when(addressService.getAddressByUserId(anyInt())).thenReturn(addressResponseList);
    ResponseEntity<?> response = addressController.getAddressByUserId(1);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(addressResponseList, response.getBody());
  }

  @Test
  void testDeleteAddress() {
    when(addressService.deleteAddress(anyInt())).thenReturn("Address deleted successfully");
    ResponseEntity<?> response = addressController.deleteAddress(1);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals("Address deleted successfully", response.getBody());
  }

  @Test
  void testUpdateAddress() {
    when(addressService.updateAddress(anyInt(), any(UpdateAddressRequest.class))).thenReturn(addressResponse);
    ResponseEntity<?> response = addressController.updateAddress(1, updateAddressRequest);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(addressResponse, response.getBody());
  }
}
