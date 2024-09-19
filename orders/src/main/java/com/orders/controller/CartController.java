package com.orders.controller;

import com.orders.dto.ApiResponse;
import com.orders.dto.CartInDto;
import com.orders.dto.CartOutDto;
import com.orders.service.CartService;
import com.orders.util.Constant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * Controller for handling cart-related operations.
 * <p>
 * This controller provides endpoints for adding or updating a cart
 * and retrieving all carts for a user at a specific restaurant.
 * </p>
 */
@RestController
@RequestMapping("/cart")
@Slf4j
@CrossOrigin(origins = "http://localhost:3000")
public class CartController {

  /**
   * Service for handling cart-related business logic.
   * <p>
   * This service is used by the controller to perform operations such as
   * adding, updating, and retrieving carts.
   * </p>
   */
  @Autowired
  private CartService cartService;

  /**
   * Adds or updates a cart for a user at a restaurant.
   * <p>
   * This method processes a POST request to add or update a cart based on the
   * {@link CartInDto} input. The cart will be created or updated based on the user and restaurant IDs.
   * </p>
   *
   * @param cartInDto The cart input DTO containing the user ID, restaurant ID, and other cart details.
   * @return A {@link ResponseEntity} containing an {@link ApiResponse} with a success message.
   */
  @PostMapping("/addCart")
  public ResponseEntity<?> addCart(@Valid @RequestBody final CartInDto cartInDto) {
    log.info("Received request to add or update cart with userId: {} and restaurantId: {}",
      cartInDto.getUserId(), cartInDto.getRestaurantId());
    Integer cartId = cartService.addOrUpdateCart(cartInDto);
    log.info("Cart added/updated with cartId: {}", cartId);
    return new ResponseEntity<>(new ApiResponse(Constant.CART_ADDED_SUCCESS), HttpStatus.OK);
  }

  /**
   * Retrieves all carts for a given user and restaurant.
   * <p>
   * This method handles a GET request to retrieve all carts associated with a specific
   * user ID and restaurant ID.
   * </p>
   *
   * @param restaurantId The ID of the restaurant for which the carts are to be retrieved.
   * @param userId The ID of the user whose carts are to be retrieved.
   * @return A {@link ResponseEntity} containing the list of carts wrapped in {@link CartOutDto}.
   */
  @GetMapping("/getAll")
  public ResponseEntity<?> getAllCart(@RequestParam final Integer restaurantId, @RequestParam final Integer userId) {
    log.info("Received request to get all carts for userId: {} and restaurantId: {}", userId, restaurantId);
    List<CartOutDto> cartOutDtoList = cartService.getAllCart(userId, restaurantId);
    log.info("Retrieved {} carts for userId: {} and restaurantId: {}",
      cartOutDtoList.size(), userId, restaurantId);
    return new ResponseEntity<>(cartOutDtoList, HttpStatus.OK);
  }

  /**
   * Deletes a cart by its ID.
   * <p>
   * This method handles a DELETE request to remove a specific cart based on its ID.
   * It processes the request to delete the cart from the system and returns a response
   * indicating the success or failure of the operation.
   * </p>
   *
   * @param cartId The ID of the cart to be deleted. This is provided as a path variable in the URL.
   * @return A {@link ResponseEntity} containing an {@link ApiResponse} with a success or error message,
   *         and the HTTP status code representing the outcome of the operation.
   */
  @DeleteMapping("/{cartId}")
  public ResponseEntity<?> deleteCart(@PathVariable final Integer cartId) {
    log.info("Trying to delete cart with id: {}", cartId);
    ApiResponse apiResponse  = cartService.deleteCart(cartId);
    log.info("Successfully delted cart with id: {}", apiResponse);
    return new ResponseEntity<>(apiResponse, HttpStatus.OK);
  }
}
