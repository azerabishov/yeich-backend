package az.yeich.service;

import az.yeich.dto.CategoryDto;
import az.yeich.dto.RestaurantCardDto;
import az.yeich.model.Category;

import java.util.List;

public interface CategoryService {
    List<CategoryDto> getAllCategories(String acceptLanguage);
    Category getCategory(String categoryId);

}
