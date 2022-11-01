package com.footprints.businessservice.domain.board.comment.exception;

import com.footprints.businessservice.global.Exception.BaseExceptionType;
import org.springframework.http.HttpStatus;

public enum CommentExceptionType implements BaseExceptionType {
    NOT_FOUND_COMMENT(404,HttpStatus.NOT_FOUND,"댓글이 존재하지 않습니다.");

    private int errorCode;
    private HttpStatus httpStatus;
    private String errorMessage;

    CommentExceptionType(int errorCode, HttpStatus httpStatus, String errorMessage) {
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
