package az.yeich.exception;

import static az.yeich.constant.ErrorMessageConstants.RESOURCE_ACCESS_FORBIDDEN;


public class NotAllowedException extends RuntimeException{
    public NotAllowedException() {super(RESOURCE_ACCESS_FORBIDDEN);}
}
