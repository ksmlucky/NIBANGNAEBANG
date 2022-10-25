package com.footprints.businessservice.domain.transferboard.exception;

import com.footprints.businessservice.global.Exception.BaseException;
import com.footprints.businessservice.global.Exception.BaseExceptionType;

public class TransferException extends BaseException {

    private BaseExceptionType exceptionType;

    public TransferException(BaseExceptionType exceptionType) {
        this.exceptionType = exceptionType;
    }

    @Override
    public BaseExceptionType getExceptionType() {
        return null;
    }
}
