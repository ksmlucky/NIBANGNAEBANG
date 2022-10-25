package com.footprints.authservice.global.common;

import lombok.Getter;

@Getter
public class CountResponse extends MessageResponse{

    private int count;

    public CountResponse(String message, int count){
        super(message);
        this.count = count;
    }
}
