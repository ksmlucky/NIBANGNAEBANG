package com.footprints.authservice.dto.request;

import lombok.Data;

@Data
public class MemberInfoRequest {
//    private String oAuthName;
    private String name;
    private String birth;
    private String provider;
    private String providerId;
}
