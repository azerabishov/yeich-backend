package az.yeich.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "ratings")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Rating {
    @Id
    private String id;
    private Integer design;
    private Integer service;
    private Integer food;
    private Integer contingent;
    private Float star;
    private String comment;
    private String reservationId;
    private String restaurantId;
    private String userId;
    private LocalDate createdAt;
}
