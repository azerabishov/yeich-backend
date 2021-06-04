package az.yeich.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OfferDto {
    private String image;
    private String desciption;
    private String subDescription;
    private String offerHours;
    private String restaurantId;
    private String restaurantName;
    private LocalDateTime expiredAt;
}
