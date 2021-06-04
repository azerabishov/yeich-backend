package az.yeich.controller;


import az.yeich.dto.AddRatingDto;
import az.yeich.security.JwtUser;
import az.yeich.service.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("rating")
@RequiredArgsConstructor
public class RatingController {

    private final RatingService ratingService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addRating(JwtUser jwtUser, AddRatingDto addRatingDto) {
        ratingService.addRating(jwtUser, addRatingDto);
    }
}
