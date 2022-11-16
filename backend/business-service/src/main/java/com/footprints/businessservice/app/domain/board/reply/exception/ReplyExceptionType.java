package com.footprints.businessservice.app.domain.board.reply.exception;

import com.footprints.businessservice.global.exception.BaseExceptionType;
import org.springframework.http.HttpStatus;

public enum ReplyExceptionType implements BaseExceptionType {
    NOT_FOUND_REPLY(404,HttpStatus.NOT_FOUND,"대댓글이 존재하지 않습니다."),
    NOT_MATCH_NICKNAME(404,HttpStatus.NOT_FOUND,"닉네임이 일치하지 않습니다.");

    private int errorCode;
    private HttpStatus httpStatus;
    private String errorMessage;

    ReplyExceptionType(int errorCode, HttpStatus httpStatus, String errorMessage) {
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
