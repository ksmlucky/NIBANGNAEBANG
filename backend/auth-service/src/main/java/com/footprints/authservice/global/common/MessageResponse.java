package com.footprints.authservice.global.common;

import lombok.Getter;

@Getter
public class MessageResponse<T> {

    private String message;

    public MessageResponse(String message) {
        this.message = message;
    }

    public MessageResponse() {
    }
}