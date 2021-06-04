package az.yeich.dto;

import az.yeich.model.Location;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RestaurantDetailDto {
    private Boolean savedStatus;
    private String collectionId;
    private String image;
    private String name;
    private String slogan;
    private String restaurantStatus;
    private String menuPdf;
    private boolean isMenuExist;
    private List<String> galery;
    private Location location;
    private String phone;
    private String averagePrice;
    private String hours;
    private String paymentMethod;
    private String dressCode;
    private Boolean parking;
    private String description;
    private String cuisines;
    private List<OfferDto> offers;
    private List<?> ratings;
}
