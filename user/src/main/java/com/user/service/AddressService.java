package com.user.service;

import com.user.dtoconversion.DtoConversion;
import com.user.entity.Address;
import com.user.exceptionhandler.NoAddressFound;
import com.user.dto.AddressInDto;
import com.user.dto.UpdateAddressInDto;
import com.user.dto.AddressOutDto;
import com.user.repository.AddressRepo;
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
  @Autowired
  private AddressRepo addressRepo;

  /**
   * Creates a new address.
   *
   * @param AddressInDto the request containing address details
   * @return an {@link AddressOutDto} representing the created address
   */
  public AddressOutDto createAddress(AddressInDto AddressInDto) {
    log.info("Creating address for userId: {}", AddressInDto.getUserId());
    Address address = DtoConversion.mapToAddress(AddressInDto);
    //Integer userId = address.getUserId();
    addressRepo.save(address);
    log.info("Address created successfully for userId: {}", AddressInDto.getUserId());
    return DtoConversion.mapToAddressOutDto(address);
  }

  /**
   * Retrieves all addresses for a given user ID.
   *
   * @param userId the ID of the user whose addresses are to be retrieved
   * @return a list of {@link AddressOutDto} containing the addresses for the specified user
   */
  public List<AddressOutDto> getAddressByUserId(Integer userId) {
    //just to check whether user existed with that userId
    log.info("Fetching addresses for userId: {}", userId);
    List<Address> addressList = addressRepo.findAllByUserId(userId);
    List<AddressOutDto> AddressOutDtoList = new ArrayList<>();
    for (Address address:addressList) {
      AddressOutDtoList.add(DtoConversion.mapToAddressOutDto(address));
    }
    log.info("Fetched {} addresses for userId: {}", AddressOutDtoList.size(), userId);
    return AddressOutDtoList;
  }

  /**
   * Deletes an address by its ID.
   *
   * @param addressId the ID of the address to be deleted
   * @return a success message indicating that the address was deleted
   * @throws NoAddressFound if no address with the given ID is found
   */
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

  /**
   * Updates an existing address.
   *
   * @param addressId the ID of the address to be updated
   * @param updateAddressInDto the request containing updated address details
   * @return an {@link AddressOutDto} representing the updated address
   * @throws NoAddressFound if no address with the given ID is found
   */
  public AddressOutDto updateAddress(Integer addressId, UpdateAddressInDto updateAddressInDto) {
    log.info("Received request to update address with addressId: {}", addressId);
    Optional<Address> optionalAddress = addressRepo.findById(addressId);
    if (!optionalAddress.isPresent()) {
      throw new NoAddressFound();
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
}

