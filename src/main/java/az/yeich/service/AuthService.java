package az.yeich.service;

import az.yeich.dto.LoginDto;
import az.yeich.dto.LoginResponseDto;
import az.yeich.dto.RegistrationDto;
import az.yeich.dto.ResetPasswordRequest;
import org.springframework.http.ResponseEntity;

import javax.mail.MessagingException;

public interface AuthService {
    void register(RegistrationDto signupRequest) throws MessagingException;
    LoginResponseDto login(LoginDto loginRequest);
    LoginResponseDto appleAuth(String idToken);
    void sendForgotPasswordEmail(String email) throws MessagingException;
    ResponseEntity<?> resetPassword(ResetPasswordRequest resetPasswordRequest);
    ResponseEntity<?> verifyEmail(Integer verificationCode);

}
