package az.yeich.service;

import az.yeich.dto.CollectionDto;
import az.yeich.dto.RestaurantCardDto;
import az.yeich.security.JwtUser;

import java.util.List;

public interface CollectionService {
    void createCollection(JwtUser jwtUser, CollectionDto collectionDto);
    void deleteCollection(CollectionDto collectionDto);
    void updateCollection(CollectionDto collectionDto);
    List<CollectionDto> getAllCollection(JwtUser jwtUser);
    void addRestaurantToCollection(String restaurantId, String collectionId);
    void removeRetaurantFromCollection(String restaurantId, String collectionId);
    List<RestaurantCardDto> getAllrestaurantFromCollection(String collectionId);
}
