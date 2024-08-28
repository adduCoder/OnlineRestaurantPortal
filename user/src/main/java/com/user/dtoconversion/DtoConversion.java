package com.user.dtoconversion;

import com.user.entity.Address;
import com.user.entity.User;
import com.user.indto.AddressRequest;
import com.user.indto.UserInDto;
import com.user.outdto.AddressResponse;
import com.user.outdto.UserOutDto;

/**
 * Utility class for converting between DTOs (Data Transfer Objects) and entities.
 * This class provides static methods to convert between different representations
 * of data such as entities and DTOs.
 */
public final class DtoConversion {
  // Private constructor to prevent instantiation
  private DtoConversion() {

  }

  /**
   * Converts a {@link UserInDto} to a {@link User} entity.
   *
   * @param userInDto the DTO containing user data
   * @return a {@link User} entity with data from the provided DTO
   */
  public static User requestToUser(UserInDto userInDto) {
    User newUser = new User();
    newUser.setEmail(userInDto.getEmail());
    newUser.setName(userInDto.getName());
    newUser.setPassword(userInDto.getPassword());
    newUser.setPhoneNo(userInDto.getPhoneNo());
    newUser.setRole(userInDto.getRole());
    return newUser;
  }


  /**
   * Converts a {@link User} entity to a {@link UserOutDto}.
   *
   * @param user the entity to convert
   * @return a {@link UserOutDto} with data from the provided entity
   */
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

  /**
   * Converts an {@link AddressRequest} to an {@link Address} entity.
   *
   * @param addressRequest the DTO containing address data
   * @return an {@link Address} entity with data from the provided DTO
   */
  public static Address requestToAddress(AddressRequest addressRequest) {
    Address address = new Address();
    address.setStreet(addressRequest.getCity());
    address.setState(addressRequest.getState());
    address.setUserId(addressRequest.getUserId());
    address.setPinCode(addressRequest.getPinCode());
    address.setCity(addressRequest.getCity());
    return address;
  }

  /**
   * Converts an {@link Address} entity to an {@link AddressResponse}.
   *
   * @param address the entity to convert
   * @return an {@link AddressResponse} with data from the provided entity
   */
  public static AddressResponse addressToResponse(Address address) {
    AddressResponse addressResponse = new AddressResponse();
    addressResponse.setAddressId(address.getId());
    addressResponse.setUserId(address.getUserId());
    addressResponse.setStreet(address.getStreet());
    addressResponse.setCity(address.getCity());
    addressResponse.setState(address.getState());
    addressResponse.setPinCode(address.getPinCode());
    return addressResponse;
  }
}

