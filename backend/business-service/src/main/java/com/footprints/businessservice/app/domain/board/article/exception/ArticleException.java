package com.footprints.businessservice.app.domain.board.article.exception;

import com.footprints.businessservice.global.exception.BaseException;
import com.footprints.businessservice.global.exception.BaseExceptionType;

public class ArticleException extends BaseException {

    private BaseExceptionType exceptionType;

    public ArticleException(BaseExceptionType exceptionType) {
        this.exceptionType = exceptionType;
    }

    @Override
    public BaseExceptionType getExceptionType() {
        return exceptionType;
    }
}
