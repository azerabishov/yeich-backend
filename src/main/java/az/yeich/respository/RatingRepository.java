package az.yeich.respository;

import az.yeich.model.Rating;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingRepository extends MongoRepository<Rating, String> {
    boolean existsByUserIdAndRestaurantId(String userId, String restaurantId);
}
