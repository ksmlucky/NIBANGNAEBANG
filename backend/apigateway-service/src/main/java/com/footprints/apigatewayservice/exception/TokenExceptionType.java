package com.footprints.apigatewayservice.exception;

//import com.footprints.authservice.global.exception.BaseExceptionType;
import org.springframework.http.HttpStatus;

public enum TokenExceptionType implements BaseExceptionType {
    NOT_FOUND_TOKEN(HttpStatus.NOT_FOUND, "존재하지 않는 토큰입니다."),
    CONFLICT_TOKEN(HttpStatus.CONFLICT, "유효하지 않은 요청입니다.");

    private HttpStatus httpStatus;
    private String errorMessage;

    TokenExceptionType(HttpStatus httpStatus, String errorMessage) {
        this.httpStatus = httpStatus;
        this.errorMessage = errorMessage;
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
