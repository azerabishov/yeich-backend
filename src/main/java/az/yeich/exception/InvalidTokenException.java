package az.yeich.exception;

import static az.yeich.constant.ErrorMessageConstants.INVALID_TOKEN;

public class InvalidTokenException extends InvalidStateException{
    public InvalidTokenException() {super(INVALID_TOKEN);}
}
