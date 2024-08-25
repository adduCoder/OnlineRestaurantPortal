package com.user.service;

import com.user.dtoconversion.DtoConversion;
import com.user.entity.Address;
import com.user.exceptionhandler.NoAddressFound;
import com.user.indto.AddressRequest;
import com.user.indto.UpdateAddressRequest;
import com.user.outdto.AddressResponse;
import com.user.outdto.UserOutDto;
import com.user.repository.AddressRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AddressService {
  @Autowired
  private UserService userService;

  @Autowired
  private AddressRepo addressRepo;

  public AddressResponse createAddress(AddressRequest addressRequest) {
    Address address = DtoConversion.requestToAddress(addressRequest);
    Integer userId = address.getUserId();
    UserOutDto existedUser = userService.getUser(userId);
    addressRepo.save(address);
    return DtoConversion.addressToResponse(address);
  }

  public List<AddressResponse> getAddressByUserId(Integer userId) {
    //just to check whether user existed with that userId
    UserOutDto existedUser = userService.getUser(userId);

    List<Address> addressList = addressRepo.findAllByUserId(userId);
    List<AddressResponse> addressResponseList = new ArrayList<>();
    for (Address address:addressList) {
      addressResponseList.add(DtoConversion.addressToResponse(address));
    }
    return addressResponseList;
  }

  public String deleteAddress(Integer addressId) {
    Optional<Address> optionalAddress = addressRepo.findById(addressId);
    if (optionalAddress.isPresent() == false)
      throw new NoAddressFound();
    Address address = optionalAddress.get();
    addressRepo.delete(address);
    return "deleted Successfull!";
  }

  public AddressResponse updateAddress(Integer addressId, UpdateAddressRequest updateAddressRequest) {
    Optional<Address> optionalAddress = addressRepo.findById(addressId);
    if (!optionalAddress.isPresent()) {
      throw new NoAddressFound();
    }
    Address address = optionalAddress.get();
    address.setStreet(updateAddressRequest.getStreet());
    address.setState(updateAddressRequest.getState());
    address.setCity(updateAddressRequest.getCity());
    address.setPinCode(updateAddressRequest.getPinCode());
    addressRepo.save(address);
    return DtoConversion.addressToResponse(address);
  }
}

