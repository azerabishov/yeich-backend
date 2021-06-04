package az.yeich.respository;

import az.yeich.model.Restaurant;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface RestaurantRepository extends MongoRepository<Restaurant, String> {
    Optional<Restaurant> findOneByName(String name);

//    @Query(value = "{ $and: [{cuisines:{$all:['Azerbaijan', 'French']}}, {features: { $all: ['Delivery', 'Seaside']}}]}")
//    @Query(value = "{ cuisines:{$all:['Azerbaijan', 'French']}}")
//@Query(value = "{ $and: [{cuisines:{$all:['Azerbaijan', 'French']}}, {features: { $all: ['Delivery', 'Seaside']}}, {}]}")
    @Query(value = "{ $and: [{cuisines:{$all:?2}}, {name: /.*?0.*/}, {name: /.*?1.*/}]}")
    List<Restaurant> filterRestaurantsByCuisines(String searchKey, String metro, String[] cuisines);

    @Query(value = "{ $and: [{features:{$all:?2}}, {name: /.*?0.*/}, {name: /.*?1.*/}]}")
    List<Restaurant> filterRestaurantsByFeatures(String searchKey, String metro, String[] features);

    @Query(value = "{ $and: [{cuisines:{$all:?3}}, {features:{$all:?2}}, {name: /.*?0.*/}, {name: /.*?1.*/}]}")
    List<Restaurant> filterRestaurantsByFeaturesAndCuisines(String searchKey, String metro, String[] features, String[] cuisines);

    @Query(value = "{ isPromoted: true}")
    List<Restaurant> findPromotedRestaurants();

//    @Query(value = "{ isPromoted: true}")
//    List<Restaurant> getTopRatedRestaurants();
//
//    @Query(value = "{ isPromoted: true}")
//    List<Restaurant> getNewRestaurants();

    @Query(value = "{ $and: [{categories:{$all:?3}}]}")
    List<Restaurant> filterRestaurantsByCategories(String[] categories);


}


//String lets = "db.restaurants.find().pretty();";
