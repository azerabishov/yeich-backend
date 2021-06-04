package az.yeich.service.impl;

import az.yeich.dto.AddRatingDto;
import az.yeich.exception.RecordAlreadyExistsException;
import az.yeich.model.Rating;
import az.yeich.respository.RatingRepository;
import az.yeich.security.JwtUser;
import az.yeich.service.RatingService;
import az.yeich.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;


@Service
@RequiredArgsConstructor
public class RatingServiceImpl implements RatingService {

    private final RatingRepository ratingRepository;

    private final RestaurantService restaurantService;

    @Override
    public void addRating(JwtUser jwtUser, AddRatingDto addRatingDto) {
        if (ratingRepository.existsByUserIdAndRestaurantId(jwtUser.getId(), addRatingDto.getRestaurantId())) {
            throw new RecordAlreadyExistsException();
        }

        restaurantService.updateRestaurantRating(addRatingDto.getStar(), addRatingDto.getRestaurantId());

        Rating rating = Rating.builder()
                .design(addRatingDto.getDesign())
                .service(addRatingDto.getService())
                .food(addRatingDto.getFood())
                .contingent(addRatingDto.getContingent())
                .star(addRatingDto.getStar())
                .comment(addRatingDto.getComment())
                .reservationId(addRatingDto.getReservationId())
                .restaurantId(addRatingDto.getRestaurantId())
                .userId(jwtUser.getId())
                .createdAt(LocalDate.now())
                .build();

        ratingRepository.save(rating);
    }
}
