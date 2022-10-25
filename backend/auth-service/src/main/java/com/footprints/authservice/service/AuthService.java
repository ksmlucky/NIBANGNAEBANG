package com.footprints.authservice.service;

import com.footprints.authservice.global.jwt.TokenProvider;
import com.footprints.authservice.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final TokenProvider tokenProvider;
    private final MemberRepository memberRepository;

    public void logout(String accessToken) {
        String providerId = tokenProvider.getMemberProviderId(accessToken);

//        memberRepository.findByProviderId(providerId)
//                .orElseThrow(() -> new RuntimeException("멤버익셉션 만들자."));

        // 추가로 처리할게 있으면 적자.
//        redisService.deleteValues(uuid);
    }


}
