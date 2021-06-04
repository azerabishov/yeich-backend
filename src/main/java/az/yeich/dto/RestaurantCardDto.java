package az.yeich.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantCardDto {
    private String id;
    private String name;
    private String averagePrice;
    private String logo;
    private String openTime;
    private String closeTime;
    private String metro;
    private String averageRating;
    private String isPromoted;

}
