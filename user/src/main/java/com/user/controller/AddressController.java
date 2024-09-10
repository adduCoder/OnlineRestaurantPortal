package com.user.controller;

import com.user.dto.AddressInDto;
import com.user.dto.UpdateAddressInDto;
import com.user.dto.AddressOutDto;
import com.user.service.AddressService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * Controller for managing addresses.
 * Provides endpoints for address management operations including creation, retrieval, update, and deletion.
 */
@Slf4j
@RestController
@RequestMapping ("/address")
public class AddressController {

  @Autowired
  private AddressService addressService;


   /**
   * Adds a new address.
   *
   * @param AddressInDto the address information to add
   * @return ResponseEntity containing the details of the added address and HTTP status
   */
  @PostMapping("/add")
  public ResponseEntity<?> addAddress(@Valid @RequestBody AddressInDto AddressInDto) {
    log.info("Received request to add address for userId: {}", AddressInDto.getUserId());
    AddressOutDto AddressOutDto = addressService.createAddress(AddressInDto);
    log.info("Address added successfully for userId: {}", AddressInDto.getUserId());
    return new ResponseEntity<>(AddressOutDto, HttpStatus.CREATED);
  }

   /**
   * Retrieves addresses for a specific user.
   *
   * @param userId the ID of the user for whom to fetch addresses
   * @return ResponseEntity containing a list of addresses and HTTP status
   */
  @GetMapping("/get/{userId}")
  public ResponseEntity<?> getAddressByUserId(@PathVariable Integer userId) {
    log.info("Fetching addresses for userId: {}", userId);
    List<AddressOutDto> AddressOutDtoList = addressService.getAddressByUserId(userId);
    log.info("Fetched {} addresses for userId: {}", AddressOutDtoList.size(), userId);
    return new ResponseEntity<>(AddressOutDtoList, HttpStatus.OK);
  }

   /**
   * Deletes an address by its ID.
   *
   * @param addressId the ID of the address to delete
   * @return ResponseEntity containing a result message and HTTP status
   */
  @DeleteMapping("/delete/{addressId}")
  public ResponseEntity<?> deleteAddress(@PathVariable Integer addressId) {
    log.info("Received request to delete address with addressId: {}", addressId);
    String result = addressService.deleteAddress(addressId);
    log.info("Address with addressId: {} deleted successfully", addressId);
    return new ResponseEntity<>(result, HttpStatus.OK);
  }

  /**
   * Updates an existing address.
   *
   * @param addressId the ID of the address to update
   * @param updateAddressInDto the new address information
   * @return ResponseEntity containing the updated address details and HTTP status
   */
  @PutMapping("/update/{addressId}")
  public ResponseEntity<?> updateAddress(@PathVariable Integer addressId,
                                         @Valid @RequestBody UpdateAddressInDto updateAddressInDto) {
    log.info("Received request to update address with addressId: {}", addressId);
    AddressOutDto AddressOutDto = addressService.updateAddress(addressId, updateAddressInDto);
    log.info("Address with addressId: {} updated successfully", addressId);
    return new ResponseEntity<>(AddressOutDto, HttpStatus.OK);
  }

}




