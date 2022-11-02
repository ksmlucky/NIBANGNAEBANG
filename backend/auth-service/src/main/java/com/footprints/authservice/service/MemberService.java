package com.footprints.authservice.service;

import com.footprints.authservice.domain.Member;
import com.footprints.authservice.dto.request.MemberInfoForDiRequest;
import com.footprints.authservice.dto.request.MemberInfoRequest;
import com.footprints.authservice.dto.request.NicknameRequest;
import com.footprints.authservice.exception.MemberException;
import com.footprints.authservice.exception.MemberExceptionType;
import com.footprints.authservice.global.auth.SHA256;
import com.footprints.authservice.global.jwt.TokenProvider;
import com.footprints.authservice.global.redis.RedisService;
import com.footprints.authservice.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.NoSuchAlgorithmException;
import java.time.Duration;
import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;
    //    private final BusinessServiceClient businessServiceClient;
    private final TokenProvider tokenProvider;
    private final RedisService redisService;

    public void insertNickname(Long memberId, NicknameRequest nicknameRequest) throws MemberException {
        Member findMember = memberRepository.findById(memberId).orElseThrow(() -> new MemberException(MemberExceptionType.NOT_FOUND_MEMBER));

        findMember.updateNickname(nicknameRequest.getNickname());
    }

    public boolean checkNickname(Long memberId, NicknameRequest nicknameRequest) {
        Optional<Member> findMember = memberRepository.findByNickname(nicknameRequest.getNickname());

        if (findMember.isPresent()) {
            return true;
        }
        return false;
    }

//    public int selectMemberCount() {
//        List<Member> findMemberAll = memberRepository.findAll();
//
//        return findMemberAll.size();
//
//    }

//    public List<Long> selectAllMemberId() {
//        List<Member> findMemberAll = memberRepository.findAll();
//
//        List<Long> membersId = new ArrayList<>();
//
//        for(Member findMember : findMemberAll){
//            membersId.add(findMember.getId());
//        }
//
//
//        return membersId;
//    }

    public void updateNickname(Long memberId, NicknameRequest nicknameRequest) throws MemberException {
        Member findMember = memberRepository.findById(memberId).orElseThrow(() -> new MemberException(MemberExceptionType.NOT_FOUND_MEMBER));

        findMember.updateNickname(nicknameRequest.getNickname());
    }

    public String selectNickname(Long memberId) throws MemberException {
        Member findMember = memberRepository.findById(memberId).orElseThrow(() -> new MemberException(MemberExceptionType.NOT_FOUND_MEMBER));

        return findMember.getNickname();
    }

    public String getDi(MemberInfoForDiRequest memberInfoForDiRequest) throws MemberException, NoSuchAlgorithmException {
        String name = memberInfoForDiRequest.getName();
        String birth = memberInfoForDiRequest.getBirth();

        SHA256 sha256 = new SHA256();
        String di = sha256.getDI(name + birth);

        return di;

//        Member findMember = memberRepository.findByDi(di).orElseThrow(() -> new MemberException(MemberExceptionType.NOT_FOUND_MEMBER));

    }

    public Member signUpMember(MemberInfoRequest memberInfoRequest) {
        String provider = memberInfoRequest.getProvider();
        String providerId = memberInfoRequest.getProviderId();
        String di = memberInfoRequest.getDi();
        String nickname = memberInfoRequest.getNickname();

        Member SignUpMember = null;
        Optional<Member> findMemberByDi = memberRepository.findByDi(di);

        //유저의 di로 테이블 select했는데 안나온다? == 뭐로든 회원가입한적이 없다.
        if (findMemberByDi.isEmpty()) {
            if (provider.equals("kakao")) {
                SignUpMember = memberRepository.save(Member.builder()
                        .nickname(nickname)
                        .kakaoProvider(provider)
                        .kakaoProviderId(providerId)
                        .di(di)
                        .build());
            } else if (provider.equals("google")) {
                SignUpMember = memberRepository.save(Member.builder()
                        .nickname(nickname)
                        .googleProvider(provider)
                        .googleProviderId(providerId)
                        .di(di)
                        .build());
            }
        } else {
            if ((provider.equals("kakao") && findMemberByDi.get().getKakaoProviderId() == null)
                    || (provider.equals("google") && findMemberByDi.get().getGoogleProviderId() == null)) {
                findMemberByDi.get().updateProviderAndProviderId(provider, providerId);
            }
        }

        if (!findMemberByDi.isEmpty()) {
            return findMemberByDi.get();
        }
        return SignUpMember;


//        Member findMemberByProviderId = null;
//        // 토큰 발급하기위해서는 memberId를 알아야하지만, 카카오로 로그인했는지, 구글로 로그인했는지 모르기때문에 if문으로 나누기
//        // 카카오로 로그인했을때
//        if (provider.equals("google")) {
//            findMemberByProviderId = memberRepository.findByGoogleProviderId(providerId).orElseThrow(() -> new MemberException(MemberExceptionType.NOT_FOUND_MEMBER));
//        } else {
//            findMemberByProviderId = memberRepository.findByKakaoProviderId(providerId).orElseThrow(() -> new MemberException(MemberExceptionType.NOT_FOUND_MEMBER));
//        }
//        String accessToken = tokenProvider.createToken(providerId, findMemberByProviderId.getId());
//        attributes.put("accessToken", accessToken);
////        String url = makeRedirectUrl(accessToken, attributes);
//        return attributes;
    }

    public Map<String, String> signInMember(Long memberId) {
        Map<String, String> tokens = new HashMap<>();

        String accessToken = tokenProvider.createAccessToken(memberId);
        String refreshToken = tokenProvider.createRefreshToken(memberId);

        redisService.setValues(String.valueOf(memberId), refreshToken, Duration.ofDays(7));

        tokens.put("accessToken", accessToken);
        tokens.put("refreshToken", refreshToken);

        return tokens;
    }


    public void memberWithdrawal(Long memberId) {
        Member findMember = memberRepository.findById(memberId).orElseThrow(() -> new MemberException(MemberExceptionType.NOT_FOUND_MEMBER));

        memberRepository.deleteById(findMember.getId());
    }


}
