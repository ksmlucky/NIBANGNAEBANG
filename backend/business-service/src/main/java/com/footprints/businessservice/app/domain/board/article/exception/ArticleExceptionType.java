package com.footprints.businessservice.app.domain.board.article.exception;

import com.footprints.businessservice.global.Exception.BaseExceptionType;
import org.springframework.http.HttpStatus;

public enum ArticleExceptionType implements BaseExceptionType {
    NOT_FOUND_ARTICLE(404, HttpStatus.NOT_FOUND, "게시글이 존재하지 않습니다."),
    ALREADY_LIKED_ARTICLE(409, HttpStatus.CONFLICT, "이미 좋아한 게시글입니다."),
    NOT_LIKED_ARTICLE(409, HttpStatus.CONFLICT, "좋아한 게시글이 아닙니다."),
    ALREADY_SCRAPPED_ARTICLE(409, HttpStatus.CONFLICT, "이미 스크랩한 게시글입니다."),
    NOT_SCRAPPED_ARTICLE(409, HttpStatus.CONFLICT, "스크랩한 게시글이 아닙니다."),
    FILE_IS_NOT_IMAGE(409, HttpStatus.CONFLICT, "이미지 파일이 아닙니다.");


    private int errorCode;
    private HttpStatus httpStatus;
    private String errorMessage;

    ArticleExceptionType(int errorCode, HttpStatus httpStatus, String errorMessage) {
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
