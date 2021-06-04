package az.yeich.controller;


import az.yeich.dto.MenuDetailDto;
import az.yeich.dto.MenuDto;
import az.yeich.model.Menu;
import az.yeich.model.MenuDetail;
import az.yeich.model.MultiLanguageText;
import az.yeich.model.Restaurant;
import az.yeich.respository.MenuRepository;
import az.yeich.respository.RestaurantRepository;
import az.yeich.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("menu")
@RequiredArgsConstructor
public class MenuController {
    public final MenuService menuService;


    @GetMapping("/{menuId}")
    public List<MenuDetailDto> getMenuDetail(@RequestHeader("accept-language") String language, @PathVariable("menuId") String menuId) {
        return menuService.getMenuContent(menuId, language);
    }


}
