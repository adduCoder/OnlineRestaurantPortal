package com.restaurants.service;

import com.restaurants.dtoconversion.DtoConversion;
import com.restaurants.entities.Category;
import com.restaurants.exceptionhandler.CategoryNotFound;
import com.restaurants.indto.CategoryInDto;
import com.restaurants.outdto.CategoryOutDto;
import com.restaurants.repository.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
  @Autowired
  private CategoryRepo categoryRepo;

  public CategoryOutDto addCategory(CategoryInDto categoryInDto) {
    Category category = categoryRepo.save(DtoConversion.mapToCategory(categoryInDto));
    return DtoConversion.mapToCategoryOutDto(category);
  }

  public CategoryOutDto getCategory(Integer id) {
    Optional<Category> optionalCategory = categoryRepo.findById(id);
    if (!optionalCategory.isPresent()) {
      throw new CategoryNotFound();
    }
    return DtoConversion.mapToCategoryOutDto(optionalCategory.get());
  }

  public List<CategoryOutDto> getAllCategory(Integer restaurantId) {
    List<Category> categoryList = categoryRepo.findAllByRestaurantId(restaurantId);
    List<CategoryOutDto> categoryOutDtoList = new ArrayList<>();
    for (Category category:categoryList) {
      categoryOutDtoList.add(DtoConversion.mapToCategoryOutDto(category));
    }
    return categoryOutDtoList;
  }

  public CategoryOutDto updateCategory(Integer categoryId, CategoryInDto categoryInDto) {
    Optional<Category> optionalCategory = categoryRepo.findById(categoryId);
    if (!optionalCategory.isPresent()) {
      throw new CategoryNotFound();
    }
    Category category = optionalCategory.get();
    category.setRestaurantId(categoryInDto.getRestaurantId());
    category.setName(categoryInDto.getName());
    categoryRepo.save(category);
    return DtoConversion.mapToCategoryOutDto(category);
  }
}
