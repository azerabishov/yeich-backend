package az.yeich.exception;


import static az.yeich.constant.ErrorMessageConstants.EMAIL_ALREADY_EXISTS;

public class EmailAlreadyExistsException extends AlreadyExistsException{
    public EmailAlreadyExistsException() {
        super(EMAIL_ALREADY_EXISTS);
    }
}
