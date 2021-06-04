package az.yeich.respository;


import az.yeich.model.Sponsor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SponsorRepository extends MongoRepository<Sponsor, String> {
}
