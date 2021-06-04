package az.yeich.service;

import az.yeich.dto.FilterDto;
import az.yeich.dto.RestaurantCardDto;
import az.yeich.dto.RestaurantDetailDto;
import az.yeich.model.Place;
import az.yeich.model.Restaurant;
import az.yeich.model.RoomTable;
import az.yeich.model.Sponsor;
import az.yeich.security.JwtUser;

import java.util.List;

public interface RestaurantService {
    List<Sponsor> getSponsors();

    List<RestaurantCardDto> getRestaurantsByCategory(String categoryId);
    List<RestaurantCardDto> getAllRestaurants();
    List<RestaurantCardDto> getPromotedRestaurannts();
    List<RestaurantCardDto> getTopRatedRestaurants();
    List<RestaurantCardDto> getNewRestaurants();
    RestaurantDetailDto getRestaurantDetail(JwtUser jwtUser, String restaurantId, String language);
    Restaurant fetchRestaurantById(String restaurantId);
    List<RoomTable> getRooms(String restaurantId);
    List<Place> getPlaces(String restaurantId);
    List<RestaurantCardDto> filter(FilterDto filterDto);
    void updateRestaurantRating(Float rating, String restaurantId);
}
