package com.footprints.authservice.global.common;

import lombok.Getter;

@Getter
public class CountDataResponse<T> extends DataResponse {
    private int count;

    public CountDataResponse(String message, T data, int count) {
        super(message, data);
        this.count = count;
    }
}