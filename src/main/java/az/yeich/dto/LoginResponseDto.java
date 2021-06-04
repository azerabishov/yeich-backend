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
public class LoginResponseDto {
    private String accessToken;
    private String firstname;
    private String lastname;
    private String photo;
    private String phone;
    private LocalDateTime registrationDate;
    private Integer rezervationCount;
    private Integer commentCount;
    private Integer bonus;
    private Integer nextBonus;
}
