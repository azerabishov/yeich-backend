package az.yeich.service.impl;

import az.yeich.dto.CategoryDto;
import az.yeich.dto.RestaurantCardDto;
import az.yeich.exception.RecordNotFoundException;
import az.yeich.mapper.CustomMapper;
import az.yeich.model.Category;
import az.yeich.respository.CategoryRepository;
import az.yeich.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    private final CustomMapper customMapper;

    @Override
    public List<CategoryDto> getAllCategories(String acceptLanguage) {
        List<Category> categories = categoryRepository.findAll();
        return customMapper.categoryListToCategoryDtos(categories, acceptLanguage);
    }

    @Override
    public Category getCategory(String categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(RecordNotFoundException::new);
    }
}
