package com.user.repository;


import com.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository interface for managing {@link User} entities.
 * Provides methods for performing CRUD operations and custom queries on user data.
 */
public interface UserRepository extends JpaRepository<User, Integer> {
  /**
   * Retrieves a user by their email address.
   *
   * @param email the email address of the user to be retrieved
   * @return an {@link Optional} containing the {@link User} if found, or empty if no user exists with the given email
   */
  Optional<User> findByEmail(String email);
}

