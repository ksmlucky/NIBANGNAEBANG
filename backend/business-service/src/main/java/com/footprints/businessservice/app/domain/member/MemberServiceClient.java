package com.footprints.businessservice.app.domain.member;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "auth-service")
public interface MemberServiceClient {

    @GetMapping("/api/{memberId}/nickname")
    NicknameResponse selectNickname(@PathVariable("memberId") Long memberId);
}
