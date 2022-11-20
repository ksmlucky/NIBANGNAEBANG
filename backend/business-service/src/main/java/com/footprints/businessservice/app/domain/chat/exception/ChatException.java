package com.footprints.businessservice.app.domain.chat.exception;

import com.footprints.businessservice.global.exception.BaseException;
import com.footprints.businessservice.global.exception.BaseExceptionType;

public class ChatException extends BaseException {

    private BaseExceptionType exceptionType;

    public ChatException(BaseExceptionType exceptionType) {
        this.exceptionType = exceptionType;
    }
    @Override
    public BaseExceptionType getExceptionType() {
        return exceptionType;
    }
}
