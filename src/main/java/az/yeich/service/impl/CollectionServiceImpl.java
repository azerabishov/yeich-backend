package az.yeich.service.impl;

import az.yeich.dto.CollectionDto;
import az.yeich.dto.RestaurantCardDto;
import az.yeich.exception.RecordAlreadyExistsException;
import az.yeich.exception.RecordNotFoundException;
import az.yeich.mapper.MapStructMapper;
import az.yeich.model.Collection;
import az.yeich.model.Restaurant;
import az.yeich.model.User;
import az.yeich.respository.CollectionRepository;
import az.yeich.respository.UserRepository;
import az.yeich.security.JwtUser;
import az.yeich.service.CollectionService;
import az.yeich.service.RestaurantService;
import az.yeich.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;


@Service
@RequiredArgsConstructor
public class CollectionServiceImpl implements CollectionService {

    private final UserService userService;

    private final RestaurantService restaurantService;

    private final UserRepository userRepository;

    private final CollectionRepository collectionRepository;

    public final MapStructMapper mapStructMapper;

    @Override
    public void createCollection(JwtUser jwtUser, CollectionDto collectionDto) {
        User user = userService.fetchUserById(jwtUser.getId());
        if(collectionRepository.existsByTitle(collectionDto.getTitle())) {
            throw new RecordAlreadyExistsException();
        }

        Collection collection2 = collectionRepository.save(Collection.builder()
                .title(collectionDto.getTitle())
                .build());
        user.getCollections().add(collection2);
        userRepository.save(user);

    }

    @Override
    public void deleteCollection(CollectionDto collectionDto) {
        Collection collection = fetchCollectionById(collectionDto.getId());
        collectionRepository.delete(collection);
    }


    @Override
    public void updateCollection(CollectionDto collectionDto) {
        Collection collection = fetchCollectionById(collectionDto.getId());
        collection.setTitle(collectionDto.getTitle());
        collectionRepository.save(collection);
    }

    @Override
    public List<CollectionDto> getAllCollection(JwtUser jwtUser) {
        User user = userService.fetchUserById(jwtUser.getId());
        List<Collection> collections = user.getCollections();
        return mapStructMapper.collectionListToCollectioDtoList(collections);
    }

    @Override
    public void addRestaurantToCollection(String restaurantId, String collectionId) {
        Collection collection = fetchCollectionById(collectionId);
        Restaurant restaurant = restaurantService.fetchRestaurantById(restaurantId);
        if (collection.getRestaurants() == null) {
            collection.setRestaurants(Collections.singletonList(restaurant));
        }else {
            collection.getRestaurants().add(restaurant);
        }

        collectionRepository.save(collection);
    }

    @Override
    public void removeRetaurantFromCollection(String restaurantId, String collectionId) {
        Collection collection = fetchCollectionById(collectionId);
        Restaurant restaurant = restaurantService.fetchRestaurantById(restaurantId);
        collection.getRestaurants().remove(restaurant);
        collectionRepository.save(collection);
    }

    @Override
    public List<RestaurantCardDto> getAllrestaurantFromCollection(String collectionId) {
        Collection collection = fetchCollectionById(collectionId);
        List<Restaurant> restaurants = collection.getRestaurants();
        return mapStructMapper.restaurantListToRestaurantDetailList(restaurants);
    }



    public Collection fetchCollectionById(String collectionId) {
        return collectionRepository.findById(collectionId)
        .orElseThrow(RecordNotFoundException::new);
    }


}
