package az.yeich.controller;


import az.yeich.dto.MenuDto;
import az.yeich.dto.RestaurantCardDto;
import az.yeich.dto.RestaurantDetailDto;
import az.yeich.model.*;
import az.yeich.respository.CategoryRepository;
import az.yeich.respository.OfferRepository;
import az.yeich.respository.RestaurantRepository;
import az.yeich.security.JwtUser;
import az.yeich.service.MenuService;
import az.yeich.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


@RestController
@RequestMapping("/restaurant")
@RequiredArgsConstructor
public class RestaurantController {
    public final MenuService menuService;
    public final RestaurantService restaurantService;


    @GetMapping("/all")
    public List<RestaurantCardDto> getAllRestaurants() {
        return restaurantService.getAllRestaurants();
    }

    @GetMapping("/{restaurantId}")
    public RestaurantDetailDto getRestaurantDetail(JwtUser jwtUser, @PathVariable("restaurantId") String restaurantId, @RequestHeader("accept-language") String language) {
        return restaurantService.getRestaurantDetail(jwtUser, restaurantId, language);
    }


    @GetMapping("/{restaurantId}/menu")
    public List<MenuDto> getMenu(@RequestHeader("accept-language") String language, @PathVariable("restaurantId") String restaurantId) {
        return menuService.getMenu(restaurantId, language);
    }


    @GetMapping("/{restaurantId}/rooms")
    public List<RoomTable> getRooms(@PathVariable("restaurantId") String restaurantId) {
        return restaurantService.getRooms(restaurantId);
    }


    @GetMapping("/{restaurantId}/places")
    public List<Place> getPlaces(@PathVariable("restaurantId") String restaurantId) {
        return restaurantService.getPlaces(restaurantId);
    }






}
