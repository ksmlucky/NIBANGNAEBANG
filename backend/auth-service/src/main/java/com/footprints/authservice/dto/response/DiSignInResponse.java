package com.footprints.authservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DiSignInResponse {

    private boolean diSignIn;

    public void updateDi(boolean diSignIn){
        this.diSignIn = diSignIn;
    }
}
