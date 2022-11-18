package com.footprints.businessservice.app.domain.chat.exception;

import com.footprints.businessservice.global.exception.BaseExceptionType;
import org.springframework.http.HttpStatus;

public enum ChatExceptionType implements BaseExceptionType {
    FULL_CHATROOM(404,HttpStatus.NOT_FOUND,"인원이 가득 차서 입장이 불가능합니다."),
    ALREADY_EXIST_MEMBER(404, HttpStatus.NOT_FOUND, "인원이 가득 차서 입장이 불가능합니다.");

    private int errorCode;
    private HttpStatus httpStatus;
    private String errorMessage;

    ChatExceptionType(int errorCode, HttpStatus httpStatus, String errorMessage) {
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
