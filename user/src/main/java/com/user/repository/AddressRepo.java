package com.user.repository;

import com.user.entity.Address;
import com.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AddressRepo extends JpaRepository<Address, Integer> {
  List<Address> findAllByUserId(Integer userId);
}
