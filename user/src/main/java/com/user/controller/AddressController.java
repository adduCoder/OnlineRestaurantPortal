package com.user.controller;

import com.user.dto.AddressInDto;
import com.user.dto.AddressOutDto;
import com.user.service.AddressService;
import com.user.util.AddressOutResponse;
import com.user.util.Constant;
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

  /**
   * Service to handle business logic for address management.
   */
  @Autowired
  private AddressService addressService;



  /**
   * Adds a new address for a user.
   *
   * @param addressInDto the details of the address to be added
   * @return a response entity with a success message and HTTP status CREATED (201)
   */
  @PostMapping("/add")
  public ResponseEntity<?> addAddress(@Valid @RequestBody final AddressInDto addressInDto) {
    log.info("Received request to add address for userId: {}", addressInDto.getUserId());
    addressService.createAddress(addressInDto);
    log.info("Address added successfully for userId: {}", addressInDto.getUserId());
    AddressOutResponse addressOutResponse = new AddressOutResponse();
    addressOutResponse.setMessage(Constant.ADDRESS_ADDED_SUCCESS);
    return new ResponseEntity<>(addressOutResponse, HttpStatus.CREATED);
  }

  /**
   * Retrieves all addresses for a specific user.
   *
   * @param userId the ID of the user whose addresses need to be retrieved
   * @return a list of addresses for the specified user and HTTP status OK (200)
   */
  @GetMapping("/get/{userId}")
  public ResponseEntity<?> getAddressByUserId(@PathVariable final Integer userId) {
    log.info("Fetching addresses for userId: {}", userId);
    List<AddressOutDto> addressOutDtoList = addressService.getAddressByUserId(userId);
    log.info("Fetched {} addresses for userId: {}", addressOutDtoList.size(), userId);
    return new ResponseEntity<>(addressOutDtoList, HttpStatus.OK);
  }


  /**
   * Deletes an address by its ID.
   *
   * @param addressId the ID of the address to be deleted
   * @return a response entity with a success message and HTTP status OK (200)
   */
  @DeleteMapping("/delete/{addressId}")
  public ResponseEntity<?> deleteAddress(@PathVariable final Integer addressId) {
    log.info("Received request to delete address with addressId: {}", addressId);
    String result = addressService.deleteAddress(addressId);
    log.info("Address with addressId: {} deleted successfully", addressId);
    return new ResponseEntity<>(result, HttpStatus.OK);
  }


  /**
   * Updates an existing address by its ID.
   *
   * @param addressId            the ID of the address to be updated
   * @param updateAddressInDto   the new address details
   * @return a response entity with a success message and HTTP status OK (200)
   */
  @PutMapping("/update/{addressId}")
  public ResponseEntity<?> updateAddress(@PathVariable final Integer addressId,
                                         @Valid @RequestBody final AddressInDto updateAddressInDto) {
    log.info("Received request to update address with addressId: {}", addressId);
    addressService.updateAddress(addressId, updateAddressInDto);
    AddressOutResponse addressOutResponse = new AddressOutResponse();
    addressOutResponse.setMessage(Constant.ADDRESS_UPDATED_SUCCESS);
    log.info("Address with addressId: {} updated successfully", addressId);
    return new ResponseEntity<>(addressOutResponse, HttpStatus.OK);
  }


  /**
   * Retrieves an address by its ID.
   *
   * @param addressId the ID of the address to be retrieved
   * @return the details of the address and HTTP status OK (200)
   */
  @GetMapping("/getByAddress/{addressId}")
  public ResponseEntity<?> getAddressByAddressId(@PathVariable final Integer addressId) {
    log.info("Fetching address with addressId: {}", addressId);
    AddressOutDto addressOutDto = addressService.getAddressByAddressId(addressId);
    log.info("Fetched address with addressId: {}", addressId);
    return new ResponseEntity<>(addressOutDto, HttpStatus.OK);
  }

}




