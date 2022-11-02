package com.footprints.authservice.dto.request;

import lombok.Data;

@Data
public class MemberInfoRequest {

    private String di;
    private String provider;
    private String providerId;
    private String nickname;
}
