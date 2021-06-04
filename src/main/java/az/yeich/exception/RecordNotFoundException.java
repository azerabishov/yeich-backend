package az.yeich.exception;

import static az.yeich.constant.ErrorMessageConstants.RECORD_NOT_FOUND;

public class RecordNotFoundException extends NotFoundException{
    public RecordNotFoundException() {
        super(RECORD_NOT_FOUND);
    }
}
