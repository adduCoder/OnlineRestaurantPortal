package com.restaurants.service;

import com.restaurants.dto.RestaurantInDto;
import com.restaurants.dto.RestaurantOutDto;
import com.restaurants.dto.UserOutDto;
import com.restaurants.conversion.DtoConversion;
import com.restaurants.entities.Restaurant;
import com.restaurants.exception.NotFoundException;
import com.restaurants.exception.OperationNotAllowedException;
import com.restaurants.repository.RestaurantRepository;
import com.restaurants.util.Constant;
import com.restaurants.util.Role;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Service class for managing restaurant-related operations.
 * Provides methods to add, retrieve, update, and manage restaurants.
 */
@Slf4j
@Service
public class RestaurantService {

  /**
   * Repository for performing CRUD operations on the Restaurant entity.
   */
  @Autowired
  private RestaurantRepository restaurantRepository;

  /**
   * Feign client for interacting with user-related services.
   */
  @Autowired
  private UserFClient userFClient;

  /**
   * Validates the MIME type of an image file.
   *
   * @param contentType The MIME type of the file.
   * @return true if the file is a valid image format (png, jpg, jpeg), false otherwise.
   */
  private boolean isValidImageFormat(final String contentType) {
    return contentType != null
      && (contentType.equals("image/png")
      || contentType.equals("image/jpeg")
      || contentType.equals("image/jpg"));
  }

  /**
   * Formats a given string by trimming whitespace, replacing multiple spaces with a single space,
   * and converting it to lowercase.
   *
   * @param currentString The string to format.
   * @return The formatted string.
   */
  public static String stringFormatter(final String currentString) {
    if (currentString == null || currentString.isEmpty()) {
      return currentString;
    }
    String formattedString = currentString.trim();
    formattedString = formattedString.replaceAll("\\s+", " ");
    return formattedString.toLowerCase();
  }

  /**
   * Adds a new restaurant.
   *
   * @param restaurantInDto DTO containing the details of the restaurant to add.
   * @param multipartFile  An optional image file associated with the restaurant.
   * @return DTO of the added restaurant.
   * @throws NotFoundException           If the user associated with the restaurant is not found.
   * @throws OperationNotAllowedException If the user has a role of USER (not allowed to add restaurants).
   * @throws IllegalArgumentException If the image file is not in a valid format.
   */
  public RestaurantOutDto addRestaurant(final RestaurantInDto restaurantInDto, final MultipartFile multipartFile) {
    log.info("Adding new restaurant with name: {}", restaurantInDto.getRestaurantName());
    //check for userId
    Restaurant restaurant = DtoConversion.mapToRestaurant(restaurantInDto);
    UserOutDto userOutDto;
    try {
      userOutDto = userFClient.getUserById(restaurantInDto.getUserId());
    } catch (Exception e) {
      throw new NotFoundException(Constant.USER_NOT_FOUND);
    }
    if (userOutDto.getRole() == Role.USER) {
      throw new OperationNotAllowedException();
    }
    if (multipartFile != null && !multipartFile.isEmpty()) {
      String contentType = multipartFile.getContentType();

      // Check if the file is a valid image format (png, jpg, jpeg)
      if (!isValidImageFormat(contentType)) {
        throw new IllegalArgumentException(Constant.BAD_IMAGE_EXTENSION);
      }

      try {
        restaurant.setImageData(multipartFile.getBytes());
      } catch (IOException e) {
        log.error("Error occurred while processing the image file", e);
      }
    }
    restaurantRepository.save(restaurant);
    log.info("Restaurant added successfully ");
    return DtoConversion.mapToRestaurantOutDto(restaurant);
  }

  /**
   * Retrieves all restaurants for a given user.
   *
   * @param userId The ID of the user whose restaurants are to be retrieved.
   * @return A list of DTOs representing the user's restaurants.
   */
  public List<RestaurantOutDto> getAll(final Integer userId) {
    log.info("Fetching all restaurants for user ID: {}", userId);
    List<Restaurant> restaurantList = restaurantRepository.findByUserId(userId);
    List<RestaurantOutDto> restaurantOutDtoList = new ArrayList<>();
    for (Restaurant restaurant:restaurantList) {
      restaurantOutDtoList.add(DtoConversion.mapToRestaurantOutDto(restaurant));
    }
    log.info("Found {} restaurants for user ID: {}", restaurantOutDtoList.size(), userId);
    return restaurantOutDtoList;
  }

  /**
   * Retrieves all restaurants.
   *
   * @return A list of DTOs representing all restaurants.
   */
  public List<RestaurantOutDto> getAllRestros() {
    List<Restaurant> restaurantList = restaurantRepository.findAll();
    List<RestaurantOutDto> restaurantOutDtoList = new ArrayList<>();
    for (Restaurant restaurant:restaurantList) {
      restaurantOutDtoList.add(DtoConversion.mapToRestaurantOutDto(restaurant));
    }
    return restaurantOutDtoList;
  }

  /**
   * Updates an existing restaurant.
   *
   * @param restaurantId   The ID of the restaurant to update.
   * @param restaurantInDto DTO containing the updated details of the restaurant.
   * @param multipartFile  An optional image file associated with the restaurant.
   * @throws NotFoundException If the restaurant with the given ID is not found.
   * @throws NotFoundException  If the user associated with the restaurant is not found.
   * @throws OperationNotAllowedException If the user has a role of USER (not allowed to update restaurants).
   */
  public void updateRestaurant(final Integer restaurantId,
                               final RestaurantInDto restaurantInDto, final MultipartFile multipartFile) {
    Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(restaurantId);
    if (!optionalRestaurant.isPresent()) {
      throw new NotFoundException(Constant.RESTAURANT_NOT_FOUND);
    }
    UserOutDto userOutDto;
    try {
      userOutDto = userFClient.getUserById(restaurantInDto.getUserId());
    } catch (Exception e) {
      throw new NotFoundException(Constant.USER_NOT_FOUND);
    }
    if (userOutDto.getRole() == Role.USER) {
      throw new OperationNotAllowedException();
    }

    Restaurant restaurant = optionalRestaurant.get();
    restaurant.setRestaurantName(stringFormatter(restaurantInDto.getRestaurantName()));
    restaurant.setAddress(stringFormatter(restaurantInDto.getAddress()));
    restaurant.setDescription(stringFormatter(restaurantInDto.getDescription()));
    restaurant.setContactNumber(restaurantInDto.getContactNumber());
    restaurant.setAddress(stringFormatter(restaurantInDto.getAddress()));
    try {
      if (multipartFile != null && !multipartFile.isEmpty()) {
        restaurant.setImageData(multipartFile.getBytes());
        log.info("Image uploaded successfully");
      } else {
        restaurant.setImageData(null);
      }
    } catch (IOException e) {
      log.error("Error occurred while processing the image file");
    }
    restaurantRepository.save(restaurant);
  }

  /**
   * Retrieves a restaurant by its ID.
   *
   * @param restaurantId The ID of the restaurant to retrieve.
   * @return DTO representing the restaurant with the given ID.
   * @throws NotFoundException If the restaurant with the given ID is not found.
   */
  public RestaurantOutDto getRestaurantById(final Integer restaurantId) {
    Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(restaurantId);
    if (!optionalRestaurant.isPresent()) {
      throw new NotFoundException(Constant.RESTAURANT_NOT_FOUND);
    }
    Restaurant restaurant = optionalRestaurant.get();
    RestaurantOutDto restaurantOutDto = DtoConversion.mapToRestaurantOutDto(restaurant);
    return restaurantOutDto;
  }
}
