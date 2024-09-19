package com.restaurants.service;

import com.restaurants.dto.CategoryInDto;
import com.restaurants.dto.CategoryOutDto;
import com.restaurants.dto.CategoryUpdateInDto;
import com.restaurants.entities.Category;
import com.restaurants.entities.Restaurant;
import com.restaurants.exception.AlreadyExistsException;
import com.restaurants.exception.NotFoundException;
import com.restaurants.repository.CategoryRepository;
import com.restaurants.repository.RestaurantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringJUnitConfig
@SpringBootTest
public class CategoryServiceTest {

  @Mock
  private CategoryRepository categoryRepository;

  @Mock
  private RestaurantRepository restaurantRepository;

  @InjectMocks
  private CategoryService categoryService;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void testAddCategoryRestaurantNotFound() {
    CategoryInDto categoryInDto = new CategoryInDto();
    categoryInDto.setRestaurantId(1);
    categoryInDto.setName("Category1");

    when(restaurantRepository.findById(1)).thenReturn(Optional.empty());

    assertThrows(NotFoundException.class, () -> categoryService.addCategory(categoryInDto));
  }

  @Test
  public void testAddCategoryAlreadyExists() {
    CategoryInDto categoryInDto = new CategoryInDto();
    categoryInDto.setRestaurantId(1);
    categoryInDto.setName("Category1");

    Restaurant restaurant = new Restaurant();
    restaurant.setId(1);
    restaurant.setRestaurantName("Restaurant1");

    when(restaurantRepository.findById(1)).thenReturn(Optional.of(restaurant));
    when(categoryRepository.findByNameAndRestaurantId("category1", 1)).thenReturn(Optional.of(new Category()));

    assertThrows(AlreadyExistsException.class, () -> categoryService.addCategory(categoryInDto));
  }

  @Test
  public void testGetCategory() {
    Category category = new Category();
    category.setId(1);
    category.setName("category1");
    category.setRestaurantId(1);

    Restaurant restaurant = new Restaurant();
    restaurant.setId(1);
    restaurant.setRestaurantName("Restaurant1");

    when(categoryRepository.findById(1)).thenReturn(Optional.of(category));
    when(restaurantRepository.findById(1)).thenReturn(Optional.of(restaurant));

    CategoryOutDto categoryOutDto = categoryService.getCategory(1);

    assertNotNull(categoryOutDto);
    assertEquals(1, categoryOutDto.getId());
    assertEquals("category1", categoryOutDto.getName());
    assertEquals("Restaurant1", categoryOutDto.getRestaurantName());
  }

  @Test
  public void testGetCategoryNotFound() {
    when(categoryRepository.findById(1)).thenReturn(Optional.empty());

    assertThrows(NotFoundException.class, () -> categoryService.getCategory(1));
  }

  @Test
  public void testGetAllCategory() {
    List<Category> categoryList = new ArrayList<>();
    Category category1 = new Category();
    category1.setId(1);
    category1.setName("category1");
    category1.setRestaurantId(1);
    categoryList.add(category1);

    Category category2 = new Category();
    category2.setId(2);
    category2.setName("category2");
    category2.setRestaurantId(1);
    categoryList.add(category2);

    Restaurant restaurant = new Restaurant();
    restaurant.setId(1);
    restaurant.setRestaurantName("Restaurant1");

    when(restaurantRepository.findById(1)).thenReturn(Optional.of(restaurant));
    when(categoryRepository.findAllByRestaurantId(1)).thenReturn(categoryList);

    List<CategoryOutDto> categoryOutDtoList = categoryService.getAllCategory(1);

    assertNotNull(categoryOutDtoList);
    assertEquals(2, categoryOutDtoList.size());
    assertEquals("category1", categoryOutDtoList.get(0).getName());
    assertEquals("category2", categoryOutDtoList.get(1).getName());
    assertEquals("Restaurant1", categoryOutDtoList.get(0).getRestaurantName());
    assertEquals("Restaurant1", categoryOutDtoList.get(1).getRestaurantName());
  }

  @Test
  public void testGetAllCategoryRestaurantNotFound() {
    when(restaurantRepository.findById(1)).thenReturn(Optional.empty());

    assertThrows(NotFoundException.class, () -> categoryService.getAllCategory(1));
  }

  @Test
  public void testUpdateCategoryNotFound() {
    CategoryUpdateInDto categoryUpdateInDto = new CategoryUpdateInDto();
    categoryUpdateInDto.setName("UpdatedCategory");

    when(categoryRepository.findById(1)).thenReturn(Optional.empty());

    assertThrows(NotFoundException.class, () -> categoryService.updateCategory(1, categoryUpdateInDto));
  }

  @Test
  public void testUpdateCategoryAlreadyExists() {
    CategoryUpdateInDto categoryUpdateInDto = new CategoryUpdateInDto();
    categoryUpdateInDto.setName("UpdatedCategory");

    Category existingCategory = new Category();
    existingCategory.setId(1);
    existingCategory.setName("category1");
    existingCategory.setRestaurantId(1);

    Category duplicateCategory = new Category();
    duplicateCategory.setId(2);
    duplicateCategory.setName("updatedcategory");
    duplicateCategory.setRestaurantId(1);

    List<Category> categoryList = new ArrayList<>();
    categoryList.add(duplicateCategory);

    when(categoryRepository.findById(1)).thenReturn(Optional.of(existingCategory));
    when(categoryRepository.findAllByRestaurantId(1)).thenReturn(categoryList);

    assertThrows(AlreadyExistsException.class, () -> categoryService.updateCategory(1, categoryUpdateInDto));
  }
}
