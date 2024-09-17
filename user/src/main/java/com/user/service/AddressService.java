package com.user.service;

import com.user.dto.AddressInDto;
import com.user.dto.AddressOutDto;
import com.user.conversion.DtoConversion;
import com.user.entity.Address;
import com.user.exception.NotFound;
import com.user.repository.AddressRepo;
import com.user.util.Constant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Service class for managing addresses.
 * <p>
 * This service provides methods for creating, retrieving, updating, and deleting addresses.
 * It interacts with the {@link AddressRepo} repository to perform CRUD operations and uses
 * {@link DtoConversion} for converting between DTOs and entity objects.
 * </p>
 */
@Slf4j
@Service
public class AddressService {

  /**
   * Repository for accessing address data from the database.
   * <p>
   * This is used to perform CRUD operations on address entities.
   * </p>
   */
  @Autowired
  private AddressRepo addressRepo;

  /**
   * Creates a new address and saves it to the repository.
   *
   * @param addressInDto the DTO containing address data to be saved
   * @return an {@link AddressOutDto} representing the created address
   */
  public AddressOutDto createAddress(final AddressInDto addressInDto) {
    log.info("Creating address for userId: {}", addressInDto.getUserId());
    Address address = DtoConversion.mapToAddress(addressInDto);
    //Integer userId = address.getUserId();
    addressRepo.save(address);
    log.info("Address created successfully for userId: {}", addressInDto.getUserId());
    return DtoConversion.mapToAddressOutDto(address);
  }

  /**
   * Retrieves all addresses for a given user ID.
   *
   * @param userId the ID of the user whose addresses are to be retrieved
   * @return a list of {@link AddressOutDto} containing the addresses for the specified user
   */
  public List<AddressOutDto> getAddressByUserId(final Integer userId) {
    //just to check whether user existed with that userId
    log.info("Fetching addresses for userId: {}", userId);
    List<Address> addressList = addressRepo.findAllByUserId(userId);
    List<AddressOutDto> addressOutDtoList = new ArrayList<>();
    for (Address address:addressList) {
      addressOutDtoList.add(DtoConversion.mapToAddressOutDto(address));
    }
    log.info("Fetched {} addresses for userId: {}", addressOutDtoList.size(), userId);
    return addressOutDtoList;
  }

  /**
   * Deletes an address by its ID.
   *
   * @param addressId the ID of the address to be deleted
   * @return a success message indicating that the address was deleted
   */
  public String deleteAddress(final Integer addressId) {
    log.info("Received request to delete address with addressId: {}", addressId);
    Optional<Address> optionalAddress = addressRepo.findById(addressId);
    if (!optionalAddress.isPresent()) {
      throw new NotFound(Constant.NO_ADDRESS_FOUND);
    }
    Address address = optionalAddress.get();
    addressRepo.delete(address);
    log.info("Address with addressId: {} deleted successfully", addressId);
    return "deleted Successfull!";
  }

  /**
   * Updates an existing address.
   *
   * @param addressId the ID of the address to be updated
   * @param updateAddressInDto the request containing updated address details
   * @return an {@link AddressOutDto} representing the updated address
   */
  public AddressOutDto updateAddress(final Integer addressId, final AddressInDto updateAddressInDto) {
    log.info("Received request to update address with addressId: {}", addressId);
    Optional<Address> optionalAddress = addressRepo.findById(addressId);
    if (!optionalAddress.isPresent()) {
      throw new NotFound(Constant.NO_ADDRESS_FOUND);
    }
    Address address = optionalAddress.get();
    address.setStreet(updateAddressInDto.getStreet());
    address.setState(updateAddressInDto.getState());
    address.setCity(updateAddressInDto.getCity());
    address.setPinCode(updateAddressInDto.getPinCode());
    addressRepo.save(address);
    log.info("Address with addressId: {} updated successfully", addressId);
    return DtoConversion.mapToAddressOutDto(address);
  }

  /**
   * Retrieves an address by its ID.
   *
   * @param addressId the ID of the address to be retrieved
   * @return an {@link AddressOutDto} representing the retrieved address
   * @throws NotFound if no address with the specified ID is found
   */
  public AddressOutDto getAddressByAddressId(final Integer addressId) {
    Optional<Address> optionalAddress = addressRepo.findById(addressId);
    if (!optionalAddress.isPresent()) {
      throw new NotFound(Constant.NO_ADDRESS_FOUND);
    }
    Address address = optionalAddress.get();
    AddressOutDto addressOutDto = DtoConversion.mapToAddressOutDto(address);
    return addressOutDto;
  }
}

