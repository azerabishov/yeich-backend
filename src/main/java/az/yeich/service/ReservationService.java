package az.yeich.service;

import az.yeich.dto.ReservationCheckingDto;
import az.yeich.dto.TableReserveRequestDto;
import az.yeich.security.JwtUser;

import java.time.LocalTime;
import java.util.List;

public interface ReservationService {
    List<LocalTime> getRezervationHoursByDate(ReservationCheckingDto reservationCheckingDto);
    void reserveTable(JwtUser jwtUser, TableReserveRequestDto tableReserveRequestDto);
}
