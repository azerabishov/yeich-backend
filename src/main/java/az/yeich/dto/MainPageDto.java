package az.yeich.dto;

import az.yeich.model.Sponsor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MainPageDto {
    private String sponsorTitle;
    private List<Sponsor> sponsors;
    private String categoryTitle;
    private List<CategoryDto> categories;
    private String exculisivelyTitle;
    private List<RestaurantCardDto> exculisively;
    private String topRatedTitle;
    private List<RestaurantCardDto> topRatedRestaurants;
    private String newRestaurantTitle;
    private List<RestaurantCardDto> newRestaurants;
    private String followUsTitle;
    private List<FollowUsDto> followUs;
}
