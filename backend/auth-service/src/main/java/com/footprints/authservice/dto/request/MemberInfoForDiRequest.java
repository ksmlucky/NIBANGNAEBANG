package com.footprints.authservice.dto.request;

import lombok.Data;

@Data
public class MemberInfoForDiRequest {
    private String provider;
    private String providerId;
    private String name;
    private String birth;
}
