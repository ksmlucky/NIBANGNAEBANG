package com.footprints.authservice.global.auth;

import com.footprints.authservice.client.BusinessServiceClient;
import com.footprints.authservice.domain.Member;
import com.footprints.authservice.global.jwt.TokenProvider;
import com.footprints.authservice.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final TokenProvider tokenProvider;
    private final MemberRepository memberRepository;
    private final BusinessServiceClient businessServiceClient;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {

//        login 성공한 사용자 목록.
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();

        String provider = String.valueOf(oAuth2User.getAttributes().get("provider"));
        String providerId = "";
        Optional<Member> findMember = null;

        // 토큰 발급하기위해서는 memberId를 알아야하지만, 카카오로 로그인했는지, 구글로 로그인했는지 모르기때문에 if문으로 나누기
        // 카카오로 로그인했을때
        if (oAuth2User.getAttributes().get("id") == null) {
            providerId = oAuth2User.getAttributes().get("sub").toString();
            findMember = memberRepository.findByGoogleProviderId(providerId);
        } else {
            providerId = oAuth2User.getAttributes().get("id").toString();
            findMember = memberRepository.findByKakaoProviderId(providerId);
        }


        String url = makeRedirectUrl(provider, providerId, findMember);

        if (response.isCommitted()) {
            logger.debug("응답이 이미 커밋된 상태입니다. " + url + "로 리다이렉트하도록 바꿀 수 없습니다.");
            return;
        }
        getRedirectStrategy().sendRedirect(request, response, url);
    }

    private String makeRedirectUrl(String provider, String providerId, Optional<Member> findMember) {

//        String accessToken = tokenProvider.createToken(providerId, findMember.get().getId());

        return UriComponentsBuilder.fromUriString("http://k7c109.p.ssafy.io:3002/oauth")
                .queryParam("provider", provider)
                .queryParam("providerId", providerId)
//                .queryParam("accessToken", accessToken)
                .build().toUriString();
    }
}
