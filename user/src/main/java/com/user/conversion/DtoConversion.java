package com.user.conversion;

import com.user.dto.AddressInDto;
import com.user.dto.AddressOutDto;
import com.user.dto.UserInDto;
import com.user.dto.UserOutDto;
import com.user.entity.Address;
import com.user.entity.User;

import java.util.Locale;

/**
 * Utility class for converting between DTOs (Data Transfer Objects) and entities.
 * This class provides static methods to convert between different representations
 * of data such as entities and DTOs.
 */
public final class DtoConversion {


  /**
   * Private constructor to prevent instantiation of this utility class.
   * This ensures the class is used in a static context only.
   */  private DtoConversion() {

  }

  /**
   * Formats a given string by trimming leading and trailing spaces, replacing
   * multiple spaces with a single space, and converting the string to lowercase.
   *
   * @param currentString the string to format
   * @return a formatted string with single spaces and in lowercase
   */
  public static String stringFormatter(final String currentString) {
    if (currentString == null || currentString.isEmpty()) {
      return currentString;
    }
    String formattedString = currentString.trim();
    formattedString = formattedString.replaceAll("\\s+", " ");
    return formattedString.toLowerCase(Locale.ROOT);
  }

  /**
   * Converts a {@link UserInDto} to a {@link User} entity.
   *
   * @param userInDto the DTO containing user data
   * @return a {@link User} entity with data from the provided DTO
   */
  public static User mapToUser(final UserInDto userInDto) {
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
  public static UserOutDto mapToUserOutDto(final User user) {
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
   * Maps address input data to the corresponding entity used for database storage.
   *
   * @param addressInDto the DTO containing address input data
   * @return an {@link Address} entity populated with data from the DTO
   */
  public static Address mapToAddress(final AddressInDto addressInDto) {
    Address address = new Address();
    address.setStreet(stringFormatter(addressInDto.getStreet()));
    address.setState(stringFormatter(addressInDto.getState()));
    address.setUserId(addressInDto.getUserId());
    address.setPinCode(addressInDto.getPinCode());
    address.setCity(stringFormatter(addressInDto.getCity()));
    return address;
  }

  /**
   * Converts an {@link Address} entity to an {@link AddressOutDto}.
   *
   * @param address the entity to convert
   * @return an {@link AddressOutDto} with data from the provided entity
   */
  public static AddressOutDto mapToAddressOutDto(final Address address) {
    AddressOutDto addressOutDto = new AddressOutDto();
    addressOutDto.setAddressId(address.getId());
    addressOutDto.setUserId(address.getUserId());
    addressOutDto.setStreet(address.getStreet());
    addressOutDto.setCity(address.getCity());
    addressOutDto.setState(address.getState());
    addressOutDto.setPinCode(address.getPinCode());
    return addressOutDto;
  }
}

