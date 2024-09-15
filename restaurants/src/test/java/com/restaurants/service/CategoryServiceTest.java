package com.restaurants.service;

import com.restaurants.dto.CategoryInDto;
import com.restaurants.dto.CategoryOutDto;
import com.restaurants.dto.CategoryUpdateInDto;
import com.restaurants.entities.Category;
import com.restaurants.entities.Restaurant;
import com.restaurants.exceptionhandler.AlreadyExists;
import com.restaurants.exceptionhandler.NotFound;
import com.restaurants.repository.CategoryRepo;
import com.restaurants.repository.RestaurantRepo;
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
  private CategoryRepo categoryRepo;

  @Mock
  private RestaurantRepo restaurantRepo;

  @InjectMocks
  private CategoryService categoryService;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
  }
//  @Test
//  public void testAddCategory() {
//    CategoryInDto categoryInDto = new CategoryInDto();
//    categoryInDto.setRestaurantId(1);
//    categoryInDto.setName("Category1");
//
//    Category category = new Category();
//    category.setId(1);
//    category.setName("category1");
//    category.setRestaurantId(1);
//
//    Restaurant restaurant = new Restaurant();
//    restaurant.setId(1);
//    restaurant.setRestaurantName("Restaurant1");
//
//    when(restaurantRepo.findById(1)).thenReturn(Optional.of(restaurant));
//    when(categoryRepo.findByNameAndRestaurantId("category1", 1)).thenReturn(Optional.empty());
//    when(categoryRepo.save(any(Category.class))).thenReturn(category);
//
//    CategoryOutDto categoryOutDto = categoryService.addCategory(categoryInDto);
//
//    assertNotNull(categoryOutDto);
//    assertEquals(1, categoryOutDto.getId());
//    assertEquals("category1", categoryOutDto.getName());
//    assertEquals("Restaurant1", categoryOutDto.getRestaurantName());  // Ensure this is being set correctly
//  }


  @Test
  public void testAddCategoryRestaurantNotFound() {
    CategoryInDto categoryInDto = new CategoryInDto();
    categoryInDto.setRestaurantId(1);
    categoryInDto.setName("Category1");

    when(restaurantRepo.findById(1)).thenReturn(Optional.empty());

    assertThrows(NotFound.class, () -> categoryService.addCategory(categoryInDto));
  }

  @Test
  public void testAddCategoryAlreadyExists() {
    CategoryInDto categoryInDto = new CategoryInDto();
    categoryInDto.setRestaurantId(1);
    categoryInDto.setName("Category1");

    Restaurant restaurant = new Restaurant();
    restaurant.setId(1);
    restaurant.setRestaurantName("Restaurant1");

    when(restaurantRepo.findById(1)).thenReturn(Optional.of(restaurant));
    when(categoryRepo.findByNameAndRestaurantId("category1", 1)).thenReturn(Optional.of(new Category()));

    assertThrows(AlreadyExists.class, () -> categoryService.addCategory(categoryInDto));
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

    when(categoryRepo.findById(1)).thenReturn(Optional.of(category));
    when(restaurantRepo.findById(1)).thenReturn(Optional.of(restaurant));

    CategoryOutDto categoryOutDto = categoryService.getCategory(1);

    assertNotNull(categoryOutDto);
    assertEquals(1, categoryOutDto.getId());
    assertEquals("category1", categoryOutDto.getName());
    assertEquals("Restaurant1", categoryOutDto.getRestaurantName());
  }

  @Test
  public void testGetCategoryNotFound() {
    when(categoryRepo.findById(1)).thenReturn(Optional.empty());

    assertThrows(NotFound.class, () -> categoryService.getCategory(1));
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

    when(restaurantRepo.findById(1)).thenReturn(Optional.of(restaurant));
    when(categoryRepo.findAllByRestaurantId(1)).thenReturn(categoryList);

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
    when(restaurantRepo.findById(1)).thenReturn(Optional.empty());

    assertThrows(NotFound.class, () -> categoryService.getAllCategory(1));
  }

//  @Test
//  public void testUpdateCategory() {
//    CategoryUpdateInDto categoryUpdateInDto = new CategoryUpdateInDto();
//    categoryUpdateInDto.setName("UpdatedCategory");
//
//    Category category = new Category();
//    category.setId(1);
//    category.setName("category1");
//    category.setRestaurantId(1);
//
//    Restaurant restaurant = new Restaurant();
//    restaurant.setId(1);
//    restaurant.setRestaurantName("Restaurant1");
//
//    when(categoryRepo.findById(1)).thenReturn(Optional.of(category));
//   // when(categoryRepo.findAllByRestaurantId(1)).thenReturn(List.of(category));
//    when(restaurantRepo.findById(1)).thenReturn(Optional.of(restaurant));
//    when(categoryRepo.save(any(Category.class))).thenReturn(category);
//
//    CategoryOutDto categoryOutDto = categoryService.updateCategory(1, categoryUpdateInDto);
//
//    assertNotNull(categoryOutDto);
//    assertEquals(1, categoryOutDto.getId());
//    assertEquals("updatedcategory", categoryOutDto.getName());
//    assertEquals("Restaurant1", categoryOutDto.getRestaurantName());  // Ensure this is being set correctly
//  }

  @Test
  public void testUpdateCategoryNotFound() {
    CategoryUpdateInDto categoryUpdateInDto = new CategoryUpdateInDto();
    categoryUpdateInDto.setName("UpdatedCategory");

    when(categoryRepo.findById(1)).thenReturn(Optional.empty());

    assertThrows(NotFound.class, () -> categoryService.updateCategory(1, categoryUpdateInDto));
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

    when(categoryRepo.findById(1)).thenReturn(Optional.of(existingCategory));
    when(categoryRepo.findAllByRestaurantId(1)).thenReturn(categoryList);

    assertThrows(AlreadyExists.class, () -> categoryService.updateCategory(1, categoryUpdateInDto));
  }
}
