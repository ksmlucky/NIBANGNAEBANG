package com.footprints.authservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class diResponse {

    private String di;

    public void updateDi(String di){
        this.di = di;
    }
}
