package az.yeich.exception;


import static az.yeich.constant.ErrorMessageConstants.RECORD_ALREADY_EXISTS;

public class RecordAlreadyExistsException extends AlreadyExistsException{
    public RecordAlreadyExistsException() {
        super(RECORD_ALREADY_EXISTS);
    }
}
