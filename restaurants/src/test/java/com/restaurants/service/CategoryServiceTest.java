package com.restaurants.service;

import com.restaurants.entities.Category;
import com.restaurants.exceptionhandler.CategoryNotFound;
import com.restaurants.dto.indto.CategoryInDto;
import com.restaurants.dto.outdto.CategoryOutDto;
import com.restaurants.repository.CategoryRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringJUnitConfig
@SpringBootTest
public class CategoryServiceTest {

  @Mock
  private CategoryRepo categoryRepo;

  @InjectMocks
  private CategoryService categoryService;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void testAddCategory() {
    CategoryInDto categoryInDto = new CategoryInDto();
    categoryInDto.setRestaurantId(1);
    categoryInDto.setName("Category1");

    Category category = new Category();
    category.setId(1);
    category.setName("Category1");
    category.setRestaurantId(1);

    when(categoryRepo.save(any(Category.class))).thenReturn(category);

    CategoryOutDto categoryOutDto = categoryService.addCategory(categoryInDto);

    assertNotNull(categoryOutDto);
    assertEquals(1, categoryOutDto.getId());
    assertEquals("Category1", categoryOutDto.getName());
    assertEquals(1, categoryOutDto.getResturantId());
  }

  @Test
  public void testGetCategory() {
    Category category = new Category();
    category.setId(1);
    category.setName("Category1");
    category.setRestaurantId(1);

    when(categoryRepo.findById(1)).thenReturn(Optional.of(category));

    CategoryOutDto categoryOutDto = categoryService.getCategory(1);

    assertNotNull(categoryOutDto);
    assertEquals(1, categoryOutDto.getId());
    assertEquals("Category1", categoryOutDto.getName());
    assertEquals(1, categoryOutDto.getResturantId());
  }

  @Test
  public void testGetCategoryNotFound() {
    when(categoryRepo.findById(1)).thenReturn(Optional.empty());

    assertThrows(CategoryNotFound.class, () -> categoryService.getCategory(1));
  }

  @Test
  public void testGetAllCategory() {
    List<Category> categoryList = new ArrayList<>();
    Category category1 = new Category();
    category1.setId(1);
    category1.setName("Category1");
    category1.setRestaurantId(1);
    categoryList.add(category1);

    Category category2 = new Category();
    category2.setId(2);
    category2.setName("Category2");
    category2.setRestaurantId(1);
    categoryList.add(category2);

    when(categoryRepo.findAllByRestaurantId(1)).thenReturn(categoryList);

    List<CategoryOutDto> categoryOutDtoList = categoryService.getAllCategory(1);

    assertNotNull(categoryOutDtoList);
    assertEquals(2, categoryOutDtoList.size());
    assertEquals("Category1", categoryOutDtoList.get(0).getName());
    assertEquals("Category2", categoryOutDtoList.get(1).getName());
  }

  @Test
  public void testUpdateCategory() {
    CategoryInDto categoryInDto = new CategoryInDto();
    categoryInDto.setRestaurantId(2);
    categoryInDto.setName("UpdatedCategory");

    Category category = new Category();
    category.setId(1);
    category.setName("OldCategory");
    category.setRestaurantId(1);

    when(categoryRepo.findById(1)).thenReturn(Optional.of(category));
    when(categoryRepo.save(any(Category.class))).thenReturn(category);

    CategoryOutDto categoryOutDto = categoryService.updateCategory(1, categoryInDto);

    assertNotNull(categoryOutDto);
    assertEquals(1, categoryOutDto.getId());
    assertEquals("UpdatedCategory", categoryOutDto.getName());
  }

  @Test
  public void testUpdateCategoryNotFound() {
    CategoryInDto categoryInDto = new CategoryInDto();
    categoryInDto.setRestaurantId(2);
    categoryInDto.setName("UpdatedCategory");

    when(categoryRepo.findById(1)).thenReturn(Optional.empty());

    assertThrows(CategoryNotFound.class, () -> categoryService.updateCategory(1, categoryInDto));
  }
}
