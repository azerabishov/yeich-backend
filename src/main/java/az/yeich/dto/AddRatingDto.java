package az.yeich.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddRatingDto {
    private Integer design;
    private Integer service;
    private Integer food;
    private Integer contingent;
    private Float star;
    private String comment;
    private String reservationId;
    private String restaurantId;

}
