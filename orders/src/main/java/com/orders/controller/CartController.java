package com.orders.controller;

import com.orders.dto.indto.CartInDto;
import com.orders.dto.outdto.AddCartResponse;
import com.orders.dto.outdto.CartOutDto;
import com.orders.service.CartService;
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

  /**
   * Adds a cart or updates an existing cart.
   *
   * @param cartInDto The input data transfer object for adding a cart.
   * @return A response entity containing the cart ID wrapped in {@link AddCartResponse}.
   */
  @PostMapping("/addCart")
  public ResponseEntity<?> addCart(@Valid @RequestBody CartInDto cartInDto) {
    log.info("Received request to add or update cart with userId: {} and restaurantId: {}",
      cartInDto.getUserId(), cartInDto.getRestaurantId());
    Integer cartId = cartService.addOrUpdateCart(cartInDto);
    AddCartResponse addCartResponse = new AddCartResponse();
    addCartResponse.setCartId(cartId);
    log.debug("Cart added/updated with cartId: {}", cartId);
    return new ResponseEntity<>(addCartResponse, HttpStatus.OK);
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
