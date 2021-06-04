package az.yeich.mapper;


import az.yeich.dto.*;
import az.yeich.model.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MapStructMapper {
    RestaurantCardDto restaurantToRestaurantDetailDto(Restaurant restaurant);
    List<RestaurantCardDto> restaurantListToRestaurantDetailList(List<Restaurant> restaurants);

    List<RestaurantCardDto> restaurantListToRestaurantDetailList(Page<Restaurant> restaurants);

    List<CollectionDto> collectionListToCollectioDtoList(List<Collection> collections);

//    @Mapping(source = "category.restaurants", target = "restaurantCount", qualifiedByName = "restaurantsToCount")
//    List<CategoryDto> categoryToCategoryDto(List<Category> categories);


    @Named("restaurantsToCount")
    static Integer multingualToLanguage(List<Restaurant> restaurants) {
        return restaurants.size();
    }

}
