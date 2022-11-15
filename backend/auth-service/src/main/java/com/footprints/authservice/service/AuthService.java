package com.footprints.authservice.service;

import com.footprints.authservice.global.jwt.TokenProvider;
import com.footprints.authservice.global.redis.RedisService;
import com.footprints.authservice.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final TokenProvider tokenProvider;
    private final MemberRepository memberRepository;
    private final RedisService redisService;

    public void logout(String accessToken) {
        String providerId = tokenProvider.getMemberProviderId(accessToken);

//        memberRepository.findByProviderId(providerId)
//                .orElseThrow(() -> new RuntimeException("멤버익셉션 만들자."));

        // 추가로 처리할게 있으면 적자.
//        redisService.deleteValues(uuid);
    }


    public String reissueAccessToken(String memberId, String refreshToken) {

        // RT 유효성 검사
        if(!tokenProvider.validateToken(refreshToken)){
            return null;
        }

        // redis에 저장된 토큰이 맞는지 확인
        String memberRefreshToken = redisService.getValue(memberId);

        // redis에 저장된 토큰이 맞는지 확인
        if(memberRefreshToken != refreshToken){
            return null;
        }

        // AT 재발급
        String newAccessToken = tokenProvider.createAccessToken(Long.parseLong(memberId));

        return newAccessToken;

    }
}
