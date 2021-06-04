package az.yeich.controller;


import az.yeich.dto.*;
import az.yeich.service.AuthService;
import com.google.gson.Gson;
import io.jsonwebtoken.io.Decoders;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("login")
    public LoginResponseDto login(@RequestBody LoginDto loginDto) {
        return authService.login(loginDto);
    }


    @GetMapping("apple/{id}")
    public LoginResponseDto appleAuth(@PathVariable("id") String idToken) {
        return authService.appleAuth(idToken);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("register")
    public void register(@RequestBody RegistrationDto registrationDto) throws MessagingException {
        authService.register(registrationDto);
    }

    @PostMapping("/forgot/password")
    public ResponseEntity<?> forgotPassword(@RequestParam("email") String email) throws MessagingException {
        authService.sendForgotPasswordEmail(email);
        return ResponseEntity.status(HttpStatus.OK).body("Please check your email");
    }

    @PostMapping("/reset/password")
    public ResponseEntity<?> resetPassword(@RequestBody ResetPasswordRequest resetPasswordRequest) throws MessagingException {
        return authService.resetPassword(resetPasswordRequest);
    }


    @GetMapping("/verify-email")
    public ResponseEntity<?> verifyEmail(@RequestParam Integer verificationCode)  {
        return authService.verifyEmail(verificationCode);
    }
}
