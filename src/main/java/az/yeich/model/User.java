package az.yeich.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;


@Document(collection = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    private String id;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private String city;
    private String country;
    private String address;
    private String birthday;
    private String gender;
    private String phone;
    private String photo;
    private Integer bonus;
    private boolean isEmailVerified;
    private Integer emailVerificationCode;
    private LocalDateTime emailVerificationCodeExpireAt;
    private String forgotPasswordToken;
    private LocalDateTime forgotPasswordTokenExpireAt;
    private LocalDateTime joinedAt;
    private List<Social> socials;

    @DBRef
    private List<Collection> collections;

    @DBRef
    private List<Reservation> reservations;
}
