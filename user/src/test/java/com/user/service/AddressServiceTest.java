package com.user.service;

import com.user.dto.AddressOutDto;
import com.user.entity.Address;
import com.user.dto.AddressInDto;
import com.user.dto.UserOutDto;
import com.user.repository.AddressRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
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

  private List<Address> addressList;

  private Address address;
  private AddressInDto AddressInDto;
  private AddressInDto updateAddressInDto;
  private AddressOutDto addressResponse;

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
    AddressInDto = new AddressInDto();
    AddressInDto.setStreet("Tower Square");
    AddressInDto.setCity("Indore");
    AddressInDto.setState("MP");
    AddressInDto.setPinCode(452001);
    AddressInDto.setUserId(1);
    updateAddressInDto = new AddressInDto();
    updateAddressInDto.setStreet("Updated Street");
    updateAddressInDto.setCity("Updated City");
    updateAddressInDto.setState("Updated State");
    updateAddressInDto.setPinCode(123456);
    addressResponse = new AddressOutDto();
    addressResponse.setStreet("Tower Square");
    addressResponse.setCity("Indore");
    addressResponse.setState("MP");
    addressResponse.setPinCode(452001);
    addressResponse.setUserId(1);

    addressList = new ArrayList<>();
    Address address = new Address();
    address.setUserId(1);
    address.setStreet("Tower Square");
    address.setCity("Indore");
    address.setState("MP");
    address.setPinCode(452001);
    addressList.add(address);
  }

  @Test
  void testCreateAddress() {
    when(userService.getUser(anyInt())).thenReturn(new UserOutDto());
    when(addressRepo.save(any(Address.class))).thenReturn(address);
    AddressOutDto response = addressService.createAddress(AddressInDto);
//    verify(userService, times(1)).getUser(anyInt());
    verify(addressRepo, times(1)).save(any(Address.class));
  }

  @Test
  void testGetAddressByUserId() {
    when(addressRepo.findAllByUserId(anyInt())).thenReturn(addressList);

    List<AddressOutDto> responses = addressService.getAddressByUserId(1);

    assertEquals(1, responses.size());
    assertEquals("Tower Square", responses.get(0).getStreet());
    assertEquals("Indore", responses.get(0).getCity());
    assertEquals("MP", responses.get(0).getState());
    assertEquals(452001, responses.get(0).getPinCode());

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
    AddressOutDto response = addressService.updateAddress(1, updateAddressInDto);
    verify(addressRepo, times(1)).findById(anyInt());
    verify(addressRepo, times(1)).save(any(Address.class));
  }

  @Test
  void testUpdateAddressNotFound() {
    when(addressRepo.findById(anyInt())).thenReturn(Optional.empty());
    assertThrows(NoAddressFound.class, () -> addressService.updateAddress(1, updateAddressInDto));
  }
}
