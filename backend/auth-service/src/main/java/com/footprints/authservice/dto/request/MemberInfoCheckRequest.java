package com.footprints.authservice.dto.request;

import lombok.Data;

@Data
public class MemberInfoCheckRequest {
    private String name;
    private String birth;
}
