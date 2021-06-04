package az.yeich.service.impl;

import az.yeich.constant.DateTimePatternConstants;
import az.yeich.dto.*;
import az.yeich.exception.*;
import az.yeich.model.Social;
import az.yeich.model.User;
import az.yeich.respository.UserRepository;
import az.yeich.service.AuthService;
import az.yeich.service.EmailService;
import az.yeich.service.UserService;
import az.yeich.util.JwtUtil;
import az.yeich.util.StringUtil;
import com.google.gson.Gson;
import io.jsonwebtoken.io.Decoders;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Optional;


@Service
@Transactional
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DateTimePatternConstants.DATETIME);

    private final EmailService emailService;

    private final UserService userService;

    private final UserRepository userRepository;

    private final PasswordEncoder encoder;

    private final JwtUtil jwtUtil;

    @Override
    public void register(RegistrationDto registrationDto) throws MessagingException {
        userRepository.findFirstByEmail(registrationDto.getEmail())
                .ifPresent(user -> {
                    throw new EmailAlreadyExistsException();
                });

        createUser(registrationDto);
        sendEmailVerificationCode(registrationDto.getEmail());
    }


    @Override
    public LoginResponseDto login(LoginDto loginDto) {
        return userRepository.findFirstByEmail(loginDto.getEmail())
                .map(user -> getTokenIfPasswordIsValidOrElseThrow(user, loginDto))
                .orElseThrow(UserNotFoundException::new);
    }

    @Override
    public LoginResponseDto appleAuth(String idToken) {
        String payload = idToken.split("\\.")[1];// 0 is header we ignore it for now
        String decoded = new String(Decoders.BASE64.decode(payload));
        AppleIDTokenPayload idTokenPayload = new Gson().fromJson(decoded, AppleIDTokenPayload.class);
        Optional<User> userData = userRepository.findFirstByEmail(idTokenPayload.getEmail());
        User user = new User();
        Social social = new Social("apple", idTokenPayload.getSub());
        if(userData.isPresent()) {
            user = userData.get();
            if (!user.getSocials().contains(social)) {
                user.getSocials().add(social);
                user = userRepository.save(user);
            }
        } else {
            user = userRepository.save(User.builder()
                    .firstname(idTokenPayload.getName().split(" ")[0])
                    .lastname(idTokenPayload.getName().split(" ")[1])
                    .email(idTokenPayload.getEmail())
                    .password(null)
                    .build());
        }

        String jwtToken = jwtUtil.issueToken(user);
        return LoginResponseDto.builder()
                .accessToken(jwtToken)
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .photo(user.getPhoto())
                .phone(user.getPhone())
                .registrationDate(user.getJoinedAt())
                .rezervationCount(0)
                .commentCount(0)
                .bonus(user.getBonus())
                .nextBonus(0)
                .build();
    }

    @Override
    public void sendForgotPasswordEmail(String email) throws MessagingException {
        String token = StringUtil.generateToken();

        userRepository.findFirstByEmail(email)
                .map(user -> updateUserForgotPasswordToken(user, token))
                .orElseThrow(UserNotFoundException::new);

        emailService.sendForgotPasswordMail(token, email);
    }



    @Override
    public ResponseEntity<?> verifyEmail(Integer verificationCode) {
        userRepository.findFirstByEmailVerificationCode(verificationCode)
                .map(this::verifyUserEmail)
                .orElseThrow(InvalidTokenException::new);

        return ResponseEntity.status(HttpStatus.OK).body("Your password has been reset");
    }


    @Override
    public ResponseEntity<?> resetPassword(ResetPasswordRequest resetPasswordRequest) {
        userRepository.findFirstByForgotPasswordToken(resetPasswordRequest.getToken())
                .map(user -> updateUserPassword(user, resetPasswordRequest.getPassword()))
                .orElseThrow(InvalidTokenException::new);

        return ResponseEntity.status(HttpStatus.OK).body("Success");
    }


    private void createUser(RegistrationDto registrationDto) {
        userRepository.save(User.builder()
            .firstname(registrationDto.getFirstname())
            .lastname(registrationDto.getLastname())
            .email(registrationDto.getEmail())
            .password(encoder.encode(registrationDto.getPassword()))
            .build());
    }





    public LoginResponseDto getTokenIfPasswordIsValidOrElseThrow(User user, LoginDto loginDto) {
        if (user.getPassword() == null) {
            throw new InvalidUsernameOrPasswordException();
        }

        if (!encoder.matches(loginDto.getPassword(), user.getPassword())) {
            throw new InvalidUsernameOrPasswordException();
        }

        if (!user.isEmailVerified()) {
            throw new EmailNotVerifiedException();
        }

        String jwtToken = jwtUtil.issueToken(user);

        return LoginResponseDto.builder()
                .accessToken(jwtToken)
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .photo(user.getPhoto())
                .phone(user.getPhone())
                .registrationDate(user.getJoinedAt())
                .rezervationCount(0)
                .commentCount(0)
                .bonus(user.getBonus())
                .nextBonus(0)
                .build();
    }


    public User updateUserPassword(User user, String password) {
        if(user.getForgotPasswordTokenExpireAt().compareTo(LocalDateTime.now()) < 0){
            throw new InvalidTokenException();
        }


        user.setPassword(encoder.encode(password));
        return userRepository.save(user);
    }

    public User verifyUserEmail(User user) {
        if(user.getEmailVerificationCodeExpireAt().compareTo(LocalDateTime.now()) < 0){
            throw new InvalidTokenException();
        }

        user.setEmailVerified(true);
        return userRepository.save(user);
    }

    public void sendEmailVerificationCode(String email) throws MessagingException {
        int verificationCode = (int) (Math.random()*(999999-100000)) - +100000;
        userRepository.findFirstByEmail(email)
                .map(user -> updateEmailVerificationCode(user, verificationCode))
                .orElseThrow(UserNotFoundException::new);

        emailService.sendEmailVerificationMail(verificationCode, email);
    }




    public User updateUserForgotPasswordToken(User user, String token) {
        user.setForgotPasswordToken(token);
        user.setForgotPasswordTokenExpireAt(LocalDateTime.now().plusHours(2));
        return userRepository.save(user);
    }

    public User updateEmailVerificationCode(User user, Integer verificationCode) {
        user.setEmailVerificationCode(verificationCode);
        user.setEmailVerificationCodeExpireAt(LocalDateTime.now().plusHours(2));
        return userRepository.save(user);
    }
}
