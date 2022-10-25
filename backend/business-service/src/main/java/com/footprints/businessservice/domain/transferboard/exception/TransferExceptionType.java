package com.footprints.businessservice.domain.transferboard.exception;

import com.footprints.businessservice.global.Exception.BaseExceptionType;
import org.springframework.http.HttpStatus;

public enum TransferExceptionType implements BaseExceptionType {
    NOT_FOUND_BOARD(404, HttpStatus.NOT_FOUND, "존재하지 않는 게시판입니다.");

    private int errorCode;
    private HttpStatus httpStatus;
    private String errorMessage;

    TransferExceptionType(int errorCode, HttpStatus httpStatus, String errorMessage) {
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
