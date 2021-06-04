package az.yeich.service.impl;


import az.yeich.dto.ReservationCheckingDto;
import az.yeich.dto.TableReserveRequestDto;
import az.yeich.enums.ReservationStatusEnum;
import az.yeich.model.Reservation;
import az.yeich.model.Restaurant;
import az.yeich.respository.ReservationRepository;
import az.yeich.security.JwtUser;
import az.yeich.service.ReservationService;
import az.yeich.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.chrono.ChronoLocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService{

    private final RestaurantService restaurantService;

    private final ReservationRepository reservationRepository;

    @Override
    public List<LocalTime> getRezervationHoursByDate(ReservationCheckingDto reservationCheckingDto) {
        Restaurant restaurant  = restaurantService.fetchRestaurantById(reservationCheckingDto.getRestaurantId());
        List<Reservation> reservationList = reservationRepository.findReservationByReservationDateAndTableId(reservationCheckingDto.getReservatinoDate(), reservationCheckingDto.getTableId());
        List<LocalTime> availableTimesIndDay = new ArrayList<>();
        LocalTime time = LocalTime.parse(restaurant.getOpenTime());
        while (time.compareTo(LocalTime.parse(restaurant.getCloseTime())) > 0){
            availableTimesIndDay.add(time.plusHours(1));
        }

        for (LocalTime localTime: availableTimesIndDay) {
            for (Reservation reservation: reservationList) {
                if (localTime.compareTo(LocalTime.parse(reservation.getBegin()).minusHours(restaurant.getRezervationPeriod())) > 0 ||
                        localTime.compareTo(LocalTime.parse(reservation.getEnd())) < 0
                ) {
                    availableTimesIndDay.remove(localTime);
                    break;
                }
            }
        }

        return availableTimesIndDay;
    }

    @Override
    public void reserveTable(JwtUser jwtUser, TableReserveRequestDto tableReserveRequestDto) {
        Reservation reservation = Reservation.builder()
                .restaurantId(tableReserveRequestDto.getRestaurantId())
                .userId(jwtUser.getId())
                .tableId(tableReserveRequestDto.getTableId())
                .reservationDate(tableReserveRequestDto.getReservationDate())
                .begin(tableReserveRequestDto.getBegin())
                .end(tableReserveRequestDto.getEnd())
                .status(ReservationStatusEnum.PENDING)
                .guessNote(tableReserveRequestDto.getGuessNote())
                .createdAt(LocalDateTime.now())
                .build();

        reservationRepository.save(reservation);

    }
}
