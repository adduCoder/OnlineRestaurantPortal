package com.user.service;

import com.user.entity.Address;
import com.user.exceptionhandler.NoAddressFound;
import com.user.indto.AddressRequest;
import com.user.indto.UpdateAddressRequest;
import com.user.outdto.AddressResponse;
import com.user.outdto.UserOutDto;
import com.user.repository.AddressRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class AddressServiceTest {

  @Mock
  private AddressRepo addressRepo;

  @Mock
  private UserService userService;

  @InjectMocks
  private AddressService addressService;

  private Address address;
  private AddressRequest addressRequest;
  private UpdateAddressRequest updateAddressRequest;
  private AddressResponse addressResponse;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    address = new Address();
    address.setId(1);
    address.setStreet("Tower Square");
    address.setCity("Indore");
    address.setState("MP");
    address.setPinCode(452001);
    address.setUserId(1);
    addressRequest = new AddressRequest();
    addressRequest.setStreet("Tower Square");
    addressRequest.setCity("Indore");
    addressRequest.setState("MP");
    addressRequest.setPinCode(452001);
    addressRequest.setUserId(1);
    updateAddressRequest = new UpdateAddressRequest();
    updateAddressRequest.setStreet("Updated Street");
    updateAddressRequest.setCity("Updated City");
    updateAddressRequest.setState("Updated State");
    updateAddressRequest.setPinCode(123456);
    addressResponse = new AddressResponse();
    addressResponse.setStreet("Tower Square");
    addressResponse.setCity("Indore");
    addressResponse.setState("MP");
    addressResponse.setPinCode(452001);
    addressResponse.setUserId(1);
  }

  @Test
  void testCreateAddress() {
    when(userService.getUser(anyInt())).thenReturn(new UserOutDto());
    when(addressRepo.save(any(Address.class))).thenReturn(address);
    AddressResponse response = addressService.createAddress(addressRequest);
    verify(userService, times(1)).getUser(anyInt());
    verify(addressRepo, times(1)).save(any(Address.class));
  }

  @Test
  void testGetAddressByUserId() {
    when(userService.getUser(anyInt())).thenReturn(new UserOutDto());
    when(addressRepo.findAllByUserId(anyInt())).thenReturn(List.of(address));
    List<AddressResponse> responses = addressService.getAddressByUserId(1);
    assertEquals(1, responses.size());
    assertEquals(addressResponse, responses.get(0));
    verify(userService, times(1)).getUser(anyInt());
    verify(addressRepo, times(1)).findAllByUserId(anyInt());
  }

  @Test
  void testDeleteAddress() {
    when(addressRepo.findById(anyInt())).thenReturn(Optional.of(address));
    String response = addressService.deleteAddress(1);
    assertEquals("deleted Successfull!", response);
    verify(addressRepo, times(1)).delete(any(Address.class));
  }

  @Test
  void testDeleteAddressNotFound() {
    when(addressRepo.findById(anyInt())).thenReturn(Optional.empty());
    assertThrows(NoAddressFound.class, () -> addressService.deleteAddress(1));
  }

  @Test
  void testUpdateAddress() {
    when(addressRepo.findById(anyInt())).thenReturn(Optional.of(address));
    when(addressRepo.save(any(Address.class))).thenReturn(address);
    AddressResponse response = addressService.updateAddress(1, updateAddressRequest);
    verify(addressRepo, times(1)).findById(anyInt());
    verify(addressRepo, times(1)).save(any(Address.class));
  }

  @Test
  void testUpdateAddressNotFound() {
    when(addressRepo.findById(anyInt())).thenReturn(Optional.empty());
    assertThrows(NoAddressFound.class, () -> addressService.updateAddress(1, updateAddressRequest));
  }
}
