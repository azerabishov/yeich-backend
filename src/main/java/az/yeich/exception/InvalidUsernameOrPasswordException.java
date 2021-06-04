package az.yeich.exception;

import static az.yeich.constant.ErrorMessageConstants.INVALID_USERNAME_OR_PASSWORD;

public class InvalidUsernameOrPasswordException extends InvalidStateException{
    public InvalidUsernameOrPasswordException() {
        super(INVALID_USERNAME_OR_PASSWORD);
    }
}
