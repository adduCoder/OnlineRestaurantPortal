package com.user.dtoconversion;

import com.user.entity.Address;
import com.user.entity.User;
import com.user.dto.AddressInDto;
import com.user.dto.UserInDto;
import com.user.dto.AddressOutDto;
import com.user.dto.UserOutDto;

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
  public static User mapToUser(UserInDto userInDto) {
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
  public static UserOutDto mapToUserOutDto(User user) {
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
   * Converts an {@link AddressInDto} to an {@link Address} entity.
   *
   * @param AddressInDto the DTO containing address data
   * @return an {@link Address} entity with data from the provided DTO
   */
  public static Address mapToAddress(AddressInDto AddressInDto) {
    Address address = new Address();
    address.setStreet(AddressInDto.getCity());
    address.setState(AddressInDto.getState());
    address.setUserId(AddressInDto.getUserId());
    address.setPinCode(AddressInDto.getPinCode());
    address.setCity(AddressInDto.getCity());
    return address;
  }

  /**
   * Converts an {@link Address} entity to an {@link AddressOutDto}.
   *
   * @param address the entity to convert
   * @return an {@link AddressOutDto} with data from the provided entity
   */
  public static AddressOutDto mapToAddressOutDto(Address address) {
    AddressOutDto AddressOutDto = new AddressOutDto();
    AddressOutDto.setAddressId(address.getId());
    AddressOutDto.setUserId(address.getUserId());
    AddressOutDto.setStreet(address.getStreet());
    AddressOutDto.setCity(address.getCity());
    AddressOutDto.setState(address.getState());
    AddressOutDto.setPinCode(address.getPinCode());
    return AddressOutDto;
  }
}

