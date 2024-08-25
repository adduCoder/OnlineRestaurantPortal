package com.user.dtoconversion;

import com.user.entity.Address;
import com.user.entity.User;
import com.user.indto.AddressRequest;
import com.user.indto.UserInDto;
import com.user.outdto.AddressResponse;
import com.user.outdto.UserOutDto;

public class DtoConversion {
  public static User requestToUser(UserInDto userInDto) {
    User newUser = new User();
    newUser.setEmail(userInDto.getEmail());
    newUser.setName(userInDto.getName());
    newUser.setPassword(userInDto.getPassword());
    newUser.setPhoneNo(userInDto.getPhoneNo());
    newUser.setRole(userInDto.getRole());
    return newUser;
  }

  public static UserOutDto userToResponse(User user) {
    UserOutDto userOutDto = new UserOutDto();
    userOutDto.setId(user.getId());
    userOutDto.setName(user.getName());
    userOutDto.setEmail(user.getEmail());
    userOutDto.setWalletBalance(user.getWalletBalance());
    userOutDto.setPhoneNo(user.getPhoneNo());
    userOutDto.setRole(user.getRole());
    return userOutDto;
  }

  public static Address requestToAddress(AddressRequest addressRequest) {
    Address address = new Address();
    address.setStreet(addressRequest.getCity());
    address.setState(addressRequest.getState());
    address.setUserId(addressRequest.getUserId());
    address.setPinCode(addressRequest.getPinCode());
    address.setCity(addressRequest.getCity());
    return address;
  }

  public static AddressResponse addressToResponse(Address address) {
    AddressResponse addressResponse = new AddressResponse();
    addressResponse.setUserId(address.getUserId());
    addressResponse.setStreet(address.getStreet());
    addressResponse.setCity(address.getCity());
    addressResponse.setState(address.getState());
    addressResponse.setPinCode(address.getPinCode());
    return addressResponse;
  }
}

