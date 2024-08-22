package com.user.service;

import com.user.dtoConversion.DtoConversion;
import com.user.entity.Address;
import com.user.entity.User;
import com.user.exceptionHandler.NoAddressFound;
import com.user.inDto.AddressRequest;
import com.user.outDto.AddressResponse;
import com.user.outDto.UserOutDto;
import com.user.repository.AddressRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AddressService {
  @Autowired
  private UserService userService;

  @Autowired
  private AddressRepo addressRepo;

  public AddressResponse createAddress(AddressRequest addressRequest){
    Address address= DtoConversion.requestToAddress(addressRequest);
    Integer userId=address.getUserId();
    UserOutDto existedUser=userService.getUser(userId);
    addressRepo.save(address);
    return DtoConversion.addressToResponse(address);
  }

  public List<AddressResponse> getAddressByUserId(Integer userId) {
    //just to check whether user existed with that userId
    UserOutDto existedUser=userService.getUser(userId);

    List<Address> addressList=addressRepo.findAllByUserId(userId);
    List<AddressResponse> addressResponseList=new ArrayList<>();
    for(Address address:addressList){
      addressResponseList.add(DtoConversion.addressToResponse(address));
    }
    return addressResponseList;
  }

  public String deleteAddress(Integer addressId) {
    Optional<Address> optionalAddress=addressRepo.findById(addressId);
    if(optionalAddress.isPresent()==false)
      throw new NoAddressFound();
    Address address=optionalAddress.get();
    addressRepo.delete(address);
    return "deleted Successfull!";
  }
}
