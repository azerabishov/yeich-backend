package az.yeich.exception;


import static az.yeich.constant.ErrorMessageConstants.USER_NOT_FOUND;

public class UserNotFoundException extends NotFoundException {
    public UserNotFoundException() {
        super(USER_NOT_FOUND);
    }
}
