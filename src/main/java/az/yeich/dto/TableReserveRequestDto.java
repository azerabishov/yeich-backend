package az.yeich.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TableReserveRequestDto {
    private String restaurantId;
    private String tableId;
    private LocalDate reservationDate;
    private String begin;
    private String end;
    private String guessNote;
}
