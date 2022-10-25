package com.footprints.businessservice.global.common;

import lombok.Getter;

@Getter
public class DataResponse<T> extends MessageResponse {
    private T data;

    public DataResponse(T data) {
        super();
        this.data = data;
    }
}