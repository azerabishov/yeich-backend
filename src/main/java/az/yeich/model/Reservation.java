package az.yeich.model;

import az.yeich.enums.ReservationStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Document(collection = "rezervation")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reservation {
    @Id
    private String id;
    private String restaurantId;
    private String userId;
    private String tableId;
    private LocalDate reservationDate;
    private String begin;
    private String end;
    private ReservationStatusEnum status;
    private String guessNote;
    private LocalDateTime createdAt;
}
