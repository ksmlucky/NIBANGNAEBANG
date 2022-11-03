package com.footprints.businessservice.app.domain.message.exception;

import com.footprints.businessservice.global.Exception.BaseException;
import com.footprints.businessservice.global.Exception.BaseExceptionType;

public class MessageException extends BaseException {

    private BaseExceptionType exceptionType;

    public MessageException(BaseExceptionType exceptionType) {
        this.exceptionType = exceptionType;
    }
    @Override
    public BaseExceptionType getExceptionType() {
        return exceptionType;
    }
}
