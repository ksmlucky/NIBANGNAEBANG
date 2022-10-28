package com.footprints.apigatewayservice.exception;

import com.footprints.authservice.global.exception.BaseException;
import com.footprints.authservice.global.exception.BaseExceptionType;

public class TokenException extends BaseException {
    private BaseExceptionType exceptionType;

    public TokenException(BaseExceptionType exceptionType) {
        this.exceptionType = exceptionType;
    }

    @Override
    public BaseExceptionType getExceptionType() {
        return exceptionType;
    }
}