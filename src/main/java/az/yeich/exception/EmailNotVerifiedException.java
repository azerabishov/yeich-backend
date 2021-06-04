package az.yeich.exception;

import static az.yeich.constant.ErrorMessageConstants.EMAIL_NOT_VERIFIED;


public class EmailNotVerifiedException extends RuntimeException{
    public EmailNotVerifiedException() {super(EMAIL_NOT_VERIFIED);}
}
