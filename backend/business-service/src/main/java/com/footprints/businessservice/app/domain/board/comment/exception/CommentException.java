package com.footprints.businessservice.app.domain.board.comment.exception;

import com.footprints.businessservice.global.exception.BaseException;
import com.footprints.businessservice.global.exception.BaseExceptionType;

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
