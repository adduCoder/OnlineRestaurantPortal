package com.user.controller;

import com.user.indto.AddressRequest;
import com.user.indto.UpdateAddressRequest;
import com.user.outdto.AddressResponse;
import com.user.service.AddressService;
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

@RestController
@RequestMapping ("/address")
public class AddressController {

  @Autowired
  private AddressService addressService;

  @PostMapping("/add")
  public ResponseEntity<?> addAddress(@Valid @RequestBody AddressRequest addressRequest) {
    AddressResponse addressResponse = addressService.createAddress(addressRequest);
    return new ResponseEntity<>(addressResponse, HttpStatus.CREATED);
  }

  @GetMapping("/get/{userId}")
  public ResponseEntity<?> getAddressByUserId(@PathVariable Integer userId) {
    List<AddressResponse> addressResponseList = addressService.getAddressByUserId(userId);
    return new ResponseEntity<>(addressResponseList, HttpStatus.OK);
  }

  @DeleteMapping("/delete/{addressId}")
  public ResponseEntity<?> deleteAddress(@PathVariable Integer addressId) {
    String result = addressService.deleteAddress(addressId);
    return new ResponseEntity<>(result, HttpStatus.OK);
  }

  @PutMapping("/update/{addressId}")
  public ResponseEntity<?> updateAddress(@PathVariable Integer addressId,
                                         @Valid @RequestBody UpdateAddressRequest updateAddressRequest) {
    AddressResponse addressResponse = addressService.updateAddress(addressId, updateAddressRequest);
    return new ResponseEntity<>(addressResponse, HttpStatus.OK);
  }

}




