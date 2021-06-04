package az.yeich.service.impl;

import az.yeich.dto.MenuDetailDto;
import az.yeich.dto.MenuDto;
import az.yeich.exception.RecordNotFoundException;
import az.yeich.mapper.CustomMapper;
import az.yeich.mapper.MapStructMapper;
import az.yeich.model.Menu;
import az.yeich.model.MenuDetail;
import az.yeich.model.Restaurant;
import az.yeich.respository.MenuRepository;
import az.yeich.service.MenuService;
import az.yeich.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {

    private final MenuRepository menuRepository;

    private final RestaurantService restaurantService;


    private final CustomMapper customMapper;

    @Override
    public List<MenuDto> getMenu(String restaurantId, String language) {
        Restaurant restaurant = restaurantService.fetchRestaurantById(restaurantId);
        List<Menu> menus = restaurant.getMenu();
        return customMapper.menuListToMenuDto(menus, language);
    }

    @Override
    public List<MenuDetailDto> getMenuContent(String menuId, String language) {
        Menu menu = menuRepository.findById(menuId).orElseThrow(RecordNotFoundException::new);
        List<MenuDetail> menuDetail = menu.getMenuDetail();
        return customMapper.menuDetailListToMenuDetailDtoList(menuDetail, language);
    }
}
