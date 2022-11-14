package com.footprints.businessservice.app.domain.board.reply.exception;

import com.footprints.businessservice.global.exception.BaseException;
import com.footprints.businessservice.global.exception.BaseExceptionType;

public class ReplyException extends BaseException {

    private BaseExceptionType exceptionType;

    public ReplyException(BaseExceptionType exceptionType) {
        this.exceptionType = exceptionType;
    }
    @Override
    public BaseExceptionType getExceptionType() {
        return exceptionType;
    }
}
