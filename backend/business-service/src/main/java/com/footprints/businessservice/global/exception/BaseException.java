package com.footprints.businessservice.global.exception;

public abstract class BaseException extends RuntimeException {
    public abstract BaseExceptionType getExceptionType();
}
