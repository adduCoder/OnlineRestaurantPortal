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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/cart")
@Slf4j
@CrossOrigin(origins = "http://localhost:3000")

/**
 * Controller for handling cart-related operations.
 * Provides endpoints for adding a cart and retrieving all carts for a user at a specific restaurant.
 */
public class CartController {
  @Autowired
  private CartService cartService;

  @PostMapping("/addCart")
  public ResponseEntity<?> addCart(@Valid @RequestBody CartInDto cartInDto) {
    log.info("Received request to add or update cart with userId: {} and restaurantId: {}",
      cartInDto.getUserId(), cartInDto.getRestaurantId());
    Integer cartId = cartService.addOrUpdateCart(cartInDto);
    log.debug("Cart added/updated with cartId: {}", cartId);
    return new ResponseEntity<>(new ApiResponse(Constant.CART_ADDED_SUCCESS), HttpStatus.OK);
  }

  /**
   * Retrieves all carts for a given user and restaurant.
   *
   * @param restaurantId The ID of the restaurant.
   * @param userId The ID of the user.
   * @return A response entity containing the list of carts wrapped in {@link CartOutDto}.
   */
  @GetMapping("/getAll")
  public ResponseEntity<?> getAllCart(@RequestParam Integer restaurantId, @RequestParam Integer userId) {
    log.info("Received request to get all carts for userId: {} and restaurantId: {}", userId, restaurantId);
    List<CartOutDto> cartOutDtoList = cartService.getAllCart(userId, restaurantId);
    log.debug("Retrieved {} carts for userId: {} and restaurantId: {}",
      cartOutDtoList.size(), userId, restaurantId);
    return new ResponseEntity<>(cartOutDtoList, HttpStatus.OK);
  }
}
