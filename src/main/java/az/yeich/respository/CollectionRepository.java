package az.yeich.respository;

import az.yeich.model.Collection;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CollectionRepository extends MongoRepository<Collection, String> {
    boolean existsByTitle(String title);


}
