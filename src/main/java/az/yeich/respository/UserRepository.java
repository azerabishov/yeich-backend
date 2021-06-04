package az.yeich.respository;

import az.yeich.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findFirstByEmail(String email);
    Optional<User> findFirstByEmailVerificationCode(Integer verificationCode);
    Optional<User> findFirstByForgotPasswordToken(String verificationCode);
}
