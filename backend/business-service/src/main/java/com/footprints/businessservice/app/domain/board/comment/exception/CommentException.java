package com.footprints.businessservice.app.domain.board.comment.exception;

import com.footprints.businessservice.global.Exception.BaseException;
import com.footprints.businessservice.global.Exception.BaseExceptionType;

public class CommentException extends BaseException {

    private BaseExceptionType exceptionType;

    public CommentException(BaseExceptionType exceptionType) {
        this.exceptionType = exceptionType;
    }
    @Override
    public BaseExceptionType getExceptionType() {
        return exceptionType;
    }
}
