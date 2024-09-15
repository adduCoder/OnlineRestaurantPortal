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

  @Autowired
  private AddressService addressService;



  @PostMapping("/add")
  public ResponseEntity<?> addAddress(@Valid @RequestBody AddressInDto addressInDto) {
    log.info("Received request to add address for userId: {}", addressInDto.getUserId());
    addressService.createAddress(addressInDto);
    log.info("Address added successfully for userId: {}", addressInDto.getUserId());
    AddressOutResponse addressOutResponse = new AddressOutResponse();
    addressOutResponse.setMessage(Constant.ADDRESS_ADDED_SUCCESS);
    return new ResponseEntity<>(addressOutResponse, HttpStatus.CREATED);
  }

  @GetMapping("/get/{userId}")
  public ResponseEntity<?> getAddressByUserId(@PathVariable Integer userId) {
    log.info("Fetching addresses for userId: {}", userId);
    List<AddressOutDto> AddressOutDtoList = addressService.getAddressByUserId(userId);
    log.info("Fetched {} addresses for userId: {}", AddressOutDtoList.size(), userId);
    return new ResponseEntity<>(AddressOutDtoList, HttpStatus.OK);
  }


  @DeleteMapping("/delete/{addressId}")
  public ResponseEntity<?> deleteAddress(@PathVariable Integer addressId) {
    log.info("Received request to delete address with addressId: {}", addressId);
    String result = addressService.deleteAddress(addressId);
    log.info("Address with addressId: {} deleted successfully", addressId);
    return new ResponseEntity<>(result, HttpStatus.OK);
  }


  @PutMapping("/update/{addressId}")
  public ResponseEntity<?> updateAddress(@PathVariable Integer addressId,
                                         @Valid @RequestBody AddressInDto updateAddressInDto) {
    log.info("Received request to update address with addressId: {}", addressId);
    addressService.updateAddress(addressId, updateAddressInDto);
    AddressOutResponse addressOutResponse = new AddressOutResponse();
    addressOutResponse.setMessage(Constant.ADDRESS_UPDATED_SUCCESS);
    log.info("Address with addressId: {} updated successfully", addressId);
    return new ResponseEntity<>(addressOutResponse, HttpStatus.OK);
  }

  @GetMapping("/getByAddress/{addressId}")
  public ResponseEntity<?> getAddressByAddressId(@PathVariable Integer addressId) {
    AddressOutDto addressOutDto = addressService.getAddressByAddressId(addressId);
    return new ResponseEntity<>(addressOutDto, HttpStatus.OK);
  }

}




