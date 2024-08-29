package com.user.repository;

import com.user.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
/**
 * Repository interface for managing {@link Address} entities.
 * Provides methods for performing CRUD operations and custom queries on the address data.
 */
public interface AddressRepo extends JpaRepository<Address, Integer> {
  /**
   * Retrieves all addresses associated with a specific user ID.
   *
   * @param userId the ID of the user whose addresses are to be fetched
   * @return a list of {@link Address} entities associated with the given user ID
   */
  List<Address> findAllByUserId(Integer userId);
}

