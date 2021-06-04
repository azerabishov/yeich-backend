package az.yeich.service;

import az.yeich.dto.MenuDetailDto;
import az.yeich.dto.MenuDto;

import java.util.List;

public interface MenuService {
    List<MenuDto> getMenu(String restaurantId, String language);
    List<MenuDetailDto> getMenuContent(String menuId, String language);
}
