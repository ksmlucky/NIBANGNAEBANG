package com.footprints.apigatewayservice.exception;

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