package com.user.service;

import com.user.dtoconversion.DtoConversion;
import com.user.entity.Address;
import com.user.exceptionhandler.NoAddressFound;
import com.user.indto.AddressRequest;
import com.user.indto.UpdateAddressRequest;
import com.user.outdto.AddressResponse;
import com.user.repository.AddressRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class AddressService {
  @Autowired
  private AddressRepo addressRepo;

  public AddressResponse createAddress(AddressRequest addressRequest) {
    log.info("Creating address for userId: {}", addressRequest.getUserId());
    Address address = DtoConversion.requestToAddress(addressRequest);
    //Integer userId = address.getUserId();
    addressRepo.save(address);
    log.info("Address created successfully for userId: {}", addressRequest.getUserId());
    return DtoConversion.addressToResponse(address);
  }

  public List<AddressResponse> getAddressByUserId(Integer userId) {
    //just to check whether user existed with that userId
    log.info("Fetching addresses for userId: {}", userId);
    List<Address> addressList = addressRepo.findAllByUserId(userId);
    List<AddressResponse> addressResponseList = new ArrayList<>();
    for (Address address:addressList) {
      addressResponseList.add(DtoConversion.addressToResponse(address));
    }
    log.info("Fetched {} addresses for userId: {}", addressResponseList.size(), userId);
    return addressResponseList;
  }

  public String deleteAddress(Integer addressId) {
    log.info("Received request to delete address with addressId: {}", addressId);
    Optional<Address> optionalAddress = addressRepo.findById(addressId);
    if (!optionalAddress.isPresent())
      throw new NoAddressFound();
    Address address = optionalAddress.get();
    addressRepo.delete(address);
    log.info("Address with addressId: {} deleted successfully", addressId);
    return "deleted Successfull!";
  }

  public AddressResponse updateAddress(Integer addressId, UpdateAddressRequest updateAddressRequest) {
    log.info("Received request to update address with addressId: {}", addressId);
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
    log.info("Address with addressId: {} updated successfully", addressId);
    return DtoConversion.addressToResponse(address);
  }
}

