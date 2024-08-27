package com.user.controller;

import com.user.indto.AddressRequest;
import com.user.indto.UpdateAddressRequest;
import com.user.outdto.AddressResponse;
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
   * @param addressRequest the address information to add
   * @return ResponseEntity containing the details of the added address and HTTP status
   */
  @PostMapping("/add")
  public ResponseEntity<?> addAddress(@Valid @RequestBody AddressRequest addressRequest) {
    log.info("Received request to add address for userId: {}", addressRequest.getUserId());
    AddressResponse addressResponse = addressService.createAddress(addressRequest);
    log.info("Address added successfully for userId: {}", addressRequest.getUserId());
    return new ResponseEntity<>(addressResponse, HttpStatus.CREATED);
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
    List<AddressResponse> addressResponseList = addressService.getAddressByUserId(userId);
    log.info("Fetched {} addresses for userId: {}", addressResponseList.size(), userId);
    return new ResponseEntity<>(addressResponseList, HttpStatus.OK);
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
   * @param updateAddressRequest the new address information
   * @return ResponseEntity containing the updated address details and HTTP status
   */
  @PutMapping("/update/{addressId}")
  public ResponseEntity<?> updateAddress(@PathVariable Integer addressId,
                                         @Valid @RequestBody UpdateAddressRequest updateAddressRequest) {
    log.info("Received request to update address with addressId: {}", addressId);
    AddressResponse addressResponse = addressService.updateAddress(addressId, updateAddressRequest);
    log.info("Address with addressId: {} updated successfully", addressId);
    return new ResponseEntity<>(addressResponse, HttpStatus.OK);
  }

}




