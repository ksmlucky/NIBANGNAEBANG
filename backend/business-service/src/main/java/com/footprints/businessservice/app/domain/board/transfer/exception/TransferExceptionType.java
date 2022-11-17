package com.footprints.businessservice.app.domain.board.transfer.exception;

import com.footprints.businessservice.global.exception.BaseExceptionType;
import org.springframework.http.HttpStatus;

public enum TransferExceptionType implements BaseExceptionType {
    IS_NOT_REGISTER(400, HttpStatus.BAD_REQUEST, "양도를 신청한 게시글이 아닙니다."),
    ALREADY_REGISTER_ARTICLE(409, HttpStatus.CONFLICT, "이미 양도 신청한 게시글입니다"),
    IS_NOT_READY_ARTICLE(409, HttpStatus.CONFLICT, "양도가 진행중인 게시글입니다."),
    ALREADY_FINISH_TRANSFER(409, HttpStatus.CONFLICT, "이미 양도가 종료된 게시글입니다."),
    CANNOT_REGISTER(409, HttpStatus.CONFLICT, "본인의 게시글에는 신청할 수 없습니다.");


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
