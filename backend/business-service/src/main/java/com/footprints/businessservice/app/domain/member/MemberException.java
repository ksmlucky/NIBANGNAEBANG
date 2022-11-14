package com.footprints.businessservice.app.domain.member;

import com.footprints.businessservice.global.exception.BaseException;
import com.footprints.businessservice.global.exception.BaseExceptionType;

public class MemberException extends BaseException {

    private BaseExceptionType exceptionType;

    public MemberException(BaseExceptionType exceptionType) {
        this.exceptionType = exceptionType;
    }
    @Override
    public BaseExceptionType getExceptionType() {
        return exceptionType;
    }
}
