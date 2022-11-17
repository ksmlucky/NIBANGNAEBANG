package com.footprints.businessservice.app.domain.board.transfer.exception;

import com.footprints.businessservice.global.exception.BaseException;
import com.footprints.businessservice.global.exception.BaseExceptionType;

public class TransferException extends BaseException {

    private BaseExceptionType exceptionType;

    public TransferException(BaseExceptionType exceptionType) {
        this.exceptionType = exceptionType;
    }

    @Override
    public BaseExceptionType getExceptionType() {
        return exceptionType;
    }
}
