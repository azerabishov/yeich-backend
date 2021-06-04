package az.yeich.service;

import az.yeich.dto.AddRatingDto;
import az.yeich.security.JwtUser;

public interface RatingService {
    void addRating(JwtUser jwtUser, AddRatingDto addRatingDto);

}
