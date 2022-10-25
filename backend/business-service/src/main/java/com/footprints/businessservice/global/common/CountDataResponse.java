package com.footprints.businessservice.global.common;

import lombok.Getter;

@Getter
public class CountDataResponse<T> extends DataResponse {
    private int count;

    public CountDataResponse(T data, int count) {
        super(data);
        this.count = count;
    }
}