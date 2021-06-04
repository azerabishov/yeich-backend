package az.yeich.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationCheckingDto {
    private String restaurantId;
    private String tableId;
    private LocalDate reservatinoDate;
}
