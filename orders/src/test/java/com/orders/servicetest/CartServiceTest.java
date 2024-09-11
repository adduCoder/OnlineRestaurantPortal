package com.orders.servicetest;

import com.orders.dto.CartInDto;
import com.orders.dtoconversion.DtoConversion;
import com.orders.entities.Cart;
import com.orders.repo.CartRepo;
import com.orders.service.CartService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CartServiceTest {

  @Mock
  private CartRepo cartRepo;

  @Mock
  private DtoConversion dtoConversion; // Mocking DtoConversion

  @InjectMocks
  private CartService cartService;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void testAddOrUpdateCart_UpdateExistingCart() {
    CartInDto cartInDto = new CartInDto();
    cartInDto.setUserId(1);
    cartInDto.setRestaurantId(1);
    cartInDto.setFoodItemId(1);
    cartInDto.setQuantity(5);

    Cart existingCart = new Cart();
    existingCart.setUserId(1);
    existingCart.setQuantity(3);

    when(cartRepo.findByUserIdAndRestaurantIdAndFoodItemId(1, 1, 1)).thenReturn(Optional.of(existingCart));
    when(cartRepo.save(any(Cart.class))).thenReturn(existingCart);

    Integer result = cartService.addOrUpdateCart(cartInDto);

    assertEquals(1, result);
    verify(cartRepo).save(existingCart);
    assertEquals(5, existingCart.getQuantity());
  }

  @Test
  public void testAddOrUpdateCart_AddNewCart() {
    CartInDto cartInDto = new CartInDto();
    cartInDto.setUserId(1);
    cartInDto.setRestaurantId(1);
    cartInDto.setFoodItemId(1);
    cartInDto.setQuantity(5);

    when(cartRepo.findByUserIdAndRestaurantIdAndFoodItemId(1, 1, 1)).thenReturn(Optional.empty());
    when(cartRepo.save(any(Cart.class))).thenReturn(new Cart());

    Integer result = cartService.addOrUpdateCart(cartInDto);

    assertNull(result);
    verify(cartRepo).save(any(Cart.class));
  }


}
