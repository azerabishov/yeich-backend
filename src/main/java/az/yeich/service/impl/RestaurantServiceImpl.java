package az.yeich.service.impl;

import az.yeich.dto.FilterDto;
import az.yeich.dto.RestaurantCardDto;
import az.yeich.dto.RestaurantDetailDto;
import az.yeich.exception.RecordNotFoundException;
import az.yeich.mapper.CustomMapper;
import az.yeich.mapper.MapStructMapper;
import az.yeich.model.*;
import az.yeich.respository.OfferRepository;
import az.yeich.respository.RestaurantRepository;
import az.yeich.respository.SponsorRepository;
import az.yeich.respository.UserRepository;
import az.yeich.security.JwtUser;
import az.yeich.service.CategoryService;
import az.yeich.service.CollectionService;
import az.yeich.service.RestaurantService;
import az.yeich.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {

    public final RestaurantRepository restaurantRepository;

    public final SponsorRepository sponsorRepository;

    public final OfferRepository offerRepository;

    public final UserService userService;

    public final CategoryService categoryService;

    public final MapStructMapper mapStructMapper;

    private final CustomMapper customMapper;

    @Override
    public List<Sponsor> getSponsors() {
        return sponsorRepository.findAll();
    }

    @Override
    public List<RestaurantCardDto> getRestaurantsByCategory(String categoryId) {
        Category category = categoryService.getCategory(categoryId);
        return mapStructMapper.restaurantListToRestaurantDetailList(category.getRestaurants());
    }

    @Override
    public List<RestaurantCardDto> getAllRestaurants() {
        List<Restaurant> restaurants =  restaurantRepository.findAll();
        return mapStructMapper.restaurantListToRestaurantDetailList(restaurants);
    }

    @Override
    public List<RestaurantCardDto> getPromotedRestaurannts() {
        return mapStructMapper.restaurantListToRestaurantDetailList(restaurantRepository.findPromotedRestaurants());
    }

    @Override
    public List<RestaurantCardDto> getTopRatedRestaurants() {
        Page<Restaurant> restaurants = restaurantRepository.findAll(PageRequest.of(0, 5, Sort.by(Direction.ASC, "totalRating")));
        return mapStructMapper.restaurantListToRestaurantDetailList(restaurants);
    }

    @Override
    public List<RestaurantCardDto> getNewRestaurants() {
        Page<Restaurant> restaurants = restaurantRepository.findAll(PageRequest.of(0, 5, Sort.by(Direction.ASC, "joinedAt")));
        return mapStructMapper.restaurantListToRestaurantDetailList(restaurants);
    }

    @Override
    public RestaurantDetailDto getRestaurantDetail(JwtUser jwtUser, String restaurantId, String language) {
        Restaurant restaurant = fetchRestaurantById(restaurantId);

        String collectionId = checkRestaurantSaved(jwtUser, restaurantId);
        boolean savedStatus = (collectionId != null);
        boolean isMenuExist = (restaurant.getMenuPdf() != null);
        List<Offer> offers = offerRepository.findByRestaurantId(restaurantId);

        return RestaurantDetailDto.builder()
                .savedStatus(savedStatus)
                .collectionId(collectionId)
                .image(restaurant.getCoverImage())
                .name(restaurant.getName())
                .slogan(restaurant.getSlogan())
                .restaurantStatus(restaurant.getRestaurantStatus())
                .menuPdf(restaurant.getMenuPdf())
                .isMenuExist(isMenuExist)
                .galery(restaurant.getGaleries())
                .location(restaurant.getLocation())
                .phone(restaurant.getPhone())
                .averagePrice(restaurant.getAveragePrice())
                .hours(restaurant.getOpenTime() + " - " + restaurant.getCloseTime())
                .paymentMethod(restaurant.getPaymentMethod())
                .dressCode(restaurant.getDressCode())
                .parking(restaurant.getParking())
                .description(restaurant.getDescription())
                .cuisines(String.join(", ", restaurant.getCuisines()))
                .offers(customMapper.offerListToOfferDtoList(offers, language))
                .ratings(null)
                .build();
    }

    @Override
    public Restaurant fetchRestaurantById(String restaurantId) {
        return restaurantRepository.findById(restaurantId)
                .orElseThrow(RecordNotFoundException::new);
    }

    @Override
    public List<RoomTable> getRooms(String restaurantId) {
        Restaurant restaurant = fetchRestaurantById(restaurantId);
        return null;
    }

    @Override
    public List<Place> getPlaces(String restaurantId) {
        Restaurant restaurant = fetchRestaurantById(restaurantId);
        return restaurant.getPlaces();
    }

    @Override
    public List<RestaurantCardDto> filter(FilterDto filterDto) {
        List<Restaurant> restaurants = null;
        List<Restaurant> categoryRestaurants = null;
        List<Restaurant> result = new ArrayList<>();

        if(filterDto.getCuisines() == null) {
            restaurants = restaurantRepository.filterRestaurantsByFeatures(filterDto.getSearchKey(), filterDto.getMetro(), filterDto.getFeatures());
        } else if (filterDto.getFeatures() == null) {
            restaurants = restaurantRepository.filterRestaurantsByCuisines(filterDto.getSearchKey(), filterDto.getMetro(), filterDto.getCuisines());
        } else if (filterDto.getFeatures() == null && filterDto.getCuisines() == null) {
            restaurants = restaurantRepository.filterRestaurantsByFeaturesAndCuisines(filterDto.getSearchKey(), filterDto.getMetro(), filterDto.getFeatures(), filterDto.getCuisines());
        }

        if(filterDto.getCategories() != null) {
            categoryRestaurants = restaurantRepository.filterRestaurantsByCategories(filterDto.getCategories());
        }

        if (restaurants == null && categoryRestaurants == null) {
            result = new ArrayList<>();
        } else if (categoryRestaurants == null) {
            result = restaurants;
        } else if (restaurants == null) {
            result = categoryRestaurants;
        } else {
            for (Restaurant restaurant: restaurants) {
                for (Restaurant restaurant1: categoryRestaurants) {
                    if(restaurant.getId().equals(restaurant1.getId())) {
                        result.add(restaurant);
                    }
                }
            }
        }


        return mapStructMapper.restaurantListToRestaurantDetailList(result);
    }

    @Override
    public void updateRestaurantRating(Float rating, String restaurantId) {
        Restaurant restaurant = fetchRestaurantById(restaurantId);
        restaurant.setTotalRating(restaurant.getTotalRating()+rating);
        restaurant.setRatingCount(restaurant.getRatingCount()+1);
        restaurantRepository.save(restaurant);
    }


    public String checkRestaurantSaved(JwtUser jwtUser, String restaurantId) {
        User user = userService.fetchUserById(jwtUser.getId());
        for (Collection collection: user.getCollections()) {
            if(collection.getRestaurants() != null) {
                for (Restaurant restaurant: collection.getRestaurants()) {
                    if (restaurant.getId().equals(restaurantId)) {
                        return collection.getId();
                    }
                }

            }
        }

        return null;
    }



}
