package com.footprints.businessservice.domain.board.reply.exception;

import com.footprints.businessservice.global.Exception.BaseException;
import com.footprints.businessservice.global.Exception.BaseExceptionType;

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
