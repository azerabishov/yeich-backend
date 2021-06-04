package az.yeich.controller;

import az.yeich.dto.*;
import az.yeich.model.Sponsor;
import az.yeich.service.CategoryService;
import az.yeich.service.RestaurantService;
import com.sun.tools.javac.Main;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;


@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class HomeController {

    private final RestaurantService restaurantService;

    private final CategoryService categoryService;

    @GetMapping("category/{categoryId}")
    public List<RestaurantCardDto> filter(@PathVariable("categoryId") String categoryId) {
        return restaurantService.getRestaurantsByCategory(categoryId);
    }

    @GetMapping("/filter")
    public List<RestaurantCardDto> filter(@RequestBody(required = false) FilterDto filterDto) {
        return restaurantService.filter(filterDto);
    }

    @GetMapping("main-page")
    public MainPageDto getMainPage(@RequestHeader("accept-language") String language) {
        String sponsorTitle       = language.equals("en") ? ( "discover" )              : (language.equals("az") ? ( "kəşf et" )             : ( "rusca" ));
        String categoryTitle      = language.equals("en") ? ( "Categories" )            : (language.equals("az") ? ( "Kateqoriyalar" )       : ( "rusca" ));
        String exculisivelyTitle  = language.equals("en") ? ( "Exclusively on yeich" )  : (language.equals("az") ? ( "Ekskulizivli yeiçdə" ) : ( "rusca" ));
        String topRatedTitle      = language.equals("en") ? ( "Top rated" )             : (language.equals("az") ? ( "Yüksək reytinglilər" ) : ( "rusca" ));
        String newRestaurantTitle = language.equals("en") ? ( "Follow us" )             : (language.equals("az") ? ( "Bizi izlə" )           : ( "rusca" ));
        String followUsTitle      = language.equals("en") ? ( "new restaurants" )       : (language.equals("az") ? ( "Yeni Restorantlar" )   : ( "rusca" ));

        List<FollowUsDto> followUs = Arrays.asList(
                new FollowUsDto("www.instagram.com", "https://i.ibb.co/dDgKBSm/insta.jpg"),
                new FollowUsDto("www.facebook.com", "https://i.ibb.co/SXWw48b/fb.png")
                );

        List<Sponsor> sponsors = restaurantService.getSponsors();
        List<CategoryDto> categories = categoryService.getAllCategories(language);
        List<RestaurantCardDto> promotedRestaurants = restaurantService.getPromotedRestaurannts();
        List<RestaurantCardDto> topRatedRestaurants = restaurantService.getTopRatedRestaurants();
        List<RestaurantCardDto> newRestaurants = restaurantService.getNewRestaurants();


        return MainPageDto.builder()
                .sponsorTitle(sponsorTitle)
                .sponsors(sponsors)
                .categoryTitle(categoryTitle)
                .categories(categories)
                .exculisivelyTitle(exculisivelyTitle)
                .exculisively(promotedRestaurants)
                .topRatedTitle(topRatedTitle)
                .topRatedRestaurants(topRatedRestaurants)
                .newRestaurantTitle(newRestaurantTitle)
                .newRestaurants(newRestaurants)
                .followUsTitle(followUsTitle)
                .followUs(followUs)
                .build();

    }

}


//        return [
//                [
//                'name' => $title1,
//                'restaurants' => $sponsors
//                ],
//                [
//                'name' => $title2,
//                'restaurants' => $array
//                ],
//                [
//                'name' => $title3,
//                'restaurants' => $exculisively
//                ],
//                [
//                'name' => $title4,
//                'restaurants' => $toprated
//                ],
//                [
//                'name' => $title5,
//                'restaurants' => $follow_us
//                ],
//                [
//                'name' => $title6,
//                'restaurants' => $news
//                ]
//                ];