package az.yeich.controller;

import az.yeich.dto.ReservationCheckingDto;
import az.yeich.dto.TableReserveRequestDto;
import az.yeich.security.JwtUser;
import az.yeich.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.List;


@RestController
@RequestMapping("rezervation")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @GetMapping("get-hours")
    public List<LocalTime> getRezervationHoursByDate(@RequestBody ReservationCheckingDto reservationCheckingDto) {
        return reservationService.getRezervationHoursByDate(reservationCheckingDto);
    }


    @GetMapping("reserve-table")
    @ResponseStatus(HttpStatus.CREATED)
    public void reserveTable(JwtUser jwtUser, TableReserveRequestDto tableReserveRequestDto) {
        reservationService.reserveTable(jwtUser, tableReserveRequestDto);
    }
}
