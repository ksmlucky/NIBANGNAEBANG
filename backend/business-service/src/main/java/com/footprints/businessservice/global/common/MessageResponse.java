package com.footprints.businessservice.global.common;

import lombok.Getter;

@Getter
public class MessageResponse<T> {
    private String message;

    public MessageResponse() {
        this.message = "성공";
    }
}