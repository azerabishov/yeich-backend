package az.yeich.service;

import javax.mail.MessagingException;

public interface EmailService {
    void sendForgotPasswordMail(String token, String userEmail) throws MessagingException;
    void sendEmailVerificationMail(Integer verificationCode, String userEmail) throws MessagingException;

}

