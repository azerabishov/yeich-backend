package az.yeich.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "restaurants")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Restaurant {
    @Id
    private String id;
    private String name;
    private String coverImage;
    private String website;
    private String slogan;
    private String description;
    private String menuPdf;
    private Location location;
    private String averagePrice;
    private String metro;
    private String phone;
    private String email;
    private String paymentMethod;
    private String dressCode;
    private Boolean parking;
    private String openTime;
    private String closeTime;
    private Boolean rezervationConfirmation;
    private Integer rezervationMaximumDelayDate;
    private Integer rezervationCancellationPeriod;
    private Integer rezervationPrePayment;
    private Integer rezervationPeriod;
    private String restaurantStatus;
    private Boolean isPromoted;
    private Integer ratingCount;
    private Float totalRating;
    private Integer bonus;
    private LocalDateTime joinedAt;
    private List<String> galeries;
    private List<String> cuisines;
    private List<String> features;
    private List<String> categoryIds;


    @DBRef
    private List<Offer> offers;

    @DBRef
    private List<Place> places;

    @DBRef
    private List<Menu> menu;

    @DBRef
    private List<Reservation> reservations;

}
