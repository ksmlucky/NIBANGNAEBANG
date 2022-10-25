package com.footprints.authservice.global.common;

import lombok.Getter;

@Getter
public class DataResponse<T> extends MessageResponse {
    private T data;

    public DataResponse(String message, T data) {
        super(message);
        this.data = data;
    }
}