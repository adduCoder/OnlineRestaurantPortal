package com.orders.controllertest;

import com.orders.controller.CartController;
import com.orders.dto.indto.CartInDto;
import com.orders.dto.outdto.AddCartResponse;
import com.orders.dto.outdto.CartOutDto;
import com.orders.service.CartService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

class CartControllerTest {

  @Mock
  private CartService cartService;

  @InjectMocks
  private CartController cartController;

  private CartInDto cartInDto;
  private CartOutDto cartOutDto;
  private AddCartResponse addCartResponse;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);

    cartInDto = new CartInDto();
    // Initialize cartInDto with appropriate values
    cartInDto.setUserId(1);
    cartInDto.setRestaurantId(1);
    cartInDto.setFoodItemId(101);
    cartInDto.setQuantity(2);
    cartInDto.setPrice(19.99);

    cartOutDto = new CartOutDto();
    // Initialize cartOutDto with appropriate values
    cartOutDto.setId(1);
    cartOutDto.setUserId(1);
    cartOutDto.setRestaurantId(1);
    cartOutDto.setFoodItemId(101);
    cartOutDto.setQuantity(2);
    cartOutDto.setPrice(19.99);

    addCartResponse = new AddCartResponse();
    addCartResponse.setCartId(1);
  }

  @Test
  void testAddCart() {
    when(cartService.addOrUpdateCart(any(CartInDto.class))).thenReturn(1);
    ResponseEntity<?> response = cartController.addCart(cartInDto);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(addCartResponse, response.getBody());
  }

  @Test
  void testGetAllCart() {
    List<CartOutDto> cartOutDtoList = Collections.singletonList(cartOutDto);
    when(cartService.getAllCart(anyInt(), anyInt())).thenReturn(cartOutDtoList);
    ResponseEntity<?> response = cartController.getAllCart(1, 1);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(cartOutDtoList, response.getBody());
  }
}
