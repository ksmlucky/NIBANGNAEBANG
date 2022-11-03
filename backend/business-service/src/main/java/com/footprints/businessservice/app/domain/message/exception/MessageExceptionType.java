package com.footprints.businessservice.app.domain.message.exception;

import com.footprints.businessservice.global.Exception.BaseExceptionType;
import org.springframework.http.HttpStatus;

public enum MessageExceptionType implements BaseExceptionType {
    NOT_FOUND_MESSAGE(404,HttpStatus.NOT_FOUND,"쪽지가 존재하지 않습니다.");

    private int errorCode;
    private HttpStatus httpStatus;
    private String errorMessage;

    MessageExceptionType(int errorCode, HttpStatus httpStatus, String errorMessage) {
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
        this.errorMessage = errorMessage;
    }

    @Override
    public int getErrorCode() {
        return this.errorCode;
    }

    @Override
    public HttpStatus getHttpStatus() {
        return this.httpStatus;
    }

    @Override
    public String getErrorMessage() {
        return this.errorMessage;
    }
}
