package com.user.repository;

import com.user.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepo extends JpaRepository<Address, Integer> {
  List<Address> findAllByUserId(Integer userId);
}

