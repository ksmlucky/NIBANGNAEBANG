package com.footprints.businessservice.domain.board.article.exception;

import com.footprints.businessservice.global.Exception.BaseException;
import com.footprints.businessservice.global.Exception.BaseExceptionType;

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
