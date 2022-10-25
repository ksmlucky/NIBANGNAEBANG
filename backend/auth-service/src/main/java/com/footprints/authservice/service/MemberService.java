package com.footprints.authservice.service;

import com.footprints.authservice.domain.Member;
import com.footprints.authservice.exception.MemberException;
import com.footprints.authservice.exception.MemberExceptionType;
import com.footprints.authservice.global.jwt.TokenProvider;
import com.footprints.authservice.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.NoSuchAlgorithmException;
import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

//    private final MemberRepository memberRepository;
//    private final BusinessServiceClient businessServiceClient;
//    private final TokenProvider tokenProvider;
//
//    public void insertNickname(Long memberId, NicknameRequest nicknameRequest) throws MemberException {
//        Member findMember = memberRepository.findById(memberId).orElseThrow(() -> new MemberException(MemberExceptionType.NOT_FOUND_MEMBER));
//
//        findMember.updateNickname(nicknameRequest.getNickname());
//    }
//
//    public boolean checkNickname(Long memberId, NicknameRequest nicknameRequest) {
//        Optional<Member> findMember = memberRepository.findByNickname(nicknameRequest.getNickname());
//
//        boolean isDuplicateNickname;
//
//        if (findMember.isPresent()) {
//            isDuplicateNickname = true;
//        } else {
//            isDuplicateNickname = false;
//        }
//
//        return isDuplicateNickname;
//    }
//
//    public int selectMemberCount() {
//        List<Member> findMemberAll = memberRepository.findAll();
//
//        return findMemberAll.size();
//
//    }
//
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
//
//    }
//
//    public void updateNickname(Long memberId, NicknameRequest nicknameRequest) throws MemberException {
//        Member findMember = memberRepository.findById(memberId).orElseThrow(() -> new MemberException(MemberExceptionType.NOT_FOUND_MEMBER));
//
//        findMember.updateNickname(nicknameRequest.getNickname());
//    }
//
//    public String selectNickname(Long memberId) throws MemberException {
//        Member findMember = memberRepository.findById(memberId).orElseThrow(() -> new MemberException(MemberExceptionType.NOT_FOUND_MEMBER));
//
//        return findMember.getNickname();
//    }
//
//    public void checkMember(Long memberId, MemberInfoCheckRequest memberInfoCheckRequest) throws MemberException, NoSuchAlgorithmException {
//        String name = memberInfoCheckRequest.getName();
//        String birth = memberInfoCheckRequest.getBirth();
//
//        SHA256 sha256 = new SHA256();
//        String di = sha256.getDI(name + birth);
//
//        Member findMember = memberRepository.findByDi(di).orElseThrow(() -> new MemberException(MemberExceptionType.NOT_FOUND_MEMBER));
//
//    }
//
//    public Map<String, Object> insertMemberInfo(MemberInfoRequest memberInfoRequest) throws NoSuchAlgorithmException {
//        Optional<Member> findMember = null;
//
//        String provider = memberInfoRequest.getProvider();
//        String providerId = memberInfoRequest.getProviderId();
//        String name = memberInfoRequest.getName();
//        String birth = memberInfoRequest.getBirth();
//
//        SHA256 sha256 = new SHA256();
//        String di = sha256.getDI(name + birth);
//        System.out.println(di);
//
//        Map<String, Object> attributes = new HashMap<>();
//
//        if (provider.equals("google")) {
//            findMember = memberRepository.findByGoogleProviderId(providerId);
//
//            if (!findMember.isPresent()) {
//                Optional<Member> findMemberByDi = memberRepository.findByDi(di);
//
//                // 카카오로 로그인한적이 있다면
//                if (findMemberByDi.isPresent()) {
//                    findMemberByDi.get().updateProviderAndProviderId(provider, providerId);
//                    System.out.println("findMemberByDi.get().toString() " + findMemberByDi.get().toString());
//                    PaintingDtoDataResponse paintingDtoDataResponse = businessServiceClient.getSelectedPaintingList(findMemberByDi.get().getId());
//                    // 이미 회원가입 + 닉네임까지 추가 + 선호그림은 선택 x
//                    if (findMemberByDi.get().getNickname() != null && paintingDtoDataResponse.getData().toString().equals("[]")) {
//                        attributes.put("isSelectPainting", false);
//                        attributes.put("isNickname", true);
//
//                    } else if (findMemberByDi.get().getNickname() != null && !paintingDtoDataResponse.getData().toString().equals("[]")) {
//                        // 이미 회원가입 + 닉네임까지 추가 + 선호그림까지 선택 o
//                        attributes.put("isSelectPainting", true);
//                        attributes.put("isNickname", true);
//
//                    } else {
//                        // 회원가입만 되어있으면,
//                        attributes.put("isSelectPainting", false);
//                        attributes.put("isNickname", false);
//                    }
//
//                } else {
//                    memberProducer.googleSignupSend("members7", GoogleSignupDto.builder()
//                                    .name(name)
//                                    .provider(provider)
//                                    .providerId(providerId)
//                                    .di(di)
//                                    .build());
//                    System.out.println("kafka 첫 실행!!!!!!!!!!!!!!!!!!!!! db 들어갔냐!!!");
////                    memberRepository.save(Member.builder()
////                            .name(name)
////                            .googleProvider(provider)
////                            .googleProviderId(providerId)
////                            .di(di)
////                            .build());
//                    attributes.put("isSelectPainting", false);
//                    attributes.put("isNickname", false);
//                }
//            } else {
//                PaintingDtoDataResponse paintingDtoDataResponse = businessServiceClient.getSelectedPaintingList(findMember.get().getId());
//
//                // 이미 회원가입 + 닉네임까지 추가 + 선호그림은 선택 x
//                if (findMember.get().getNickname() != null && paintingDtoDataResponse.getData().toString().equals("[]")) {
//                    attributes.put("isSelectPainting", false);
//                    attributes.put("isNickname", true);
//                } else if (findMember.get().getNickname() != null && !paintingDtoDataResponse.getData().toString().equals("[]")) {
//                    // 이미 회원가입 + 닉네임까지 추가 + 선호그림까지 선택 o
//                    attributes.put("isSelectPainting", true);
//                    attributes.put("isNickname", true);
//                } else {
//                    // 회원가입만 되어있으면
//                    attributes.put("isSelectPainting", false);
//                    attributes.put("isNickname", false);
//                }
//
//            }
//
//
//        } else if (provider.equals("kakao")) {
//            findMember = memberRepository.findByKakaoProviderId(providerId);
//
//            if (!findMember.isPresent()) {
//
//                //DI
//                Optional<Member> findMemberByDi = memberRepository.findByDi(di);
//
//                // 구글로 로그인한적이 있다면
//                if (findMemberByDi.isPresent()) {
//                    findMemberByDi.get().updateProviderAndProviderId(provider, providerId);
//                    System.out.println("findMemberByDi.get().toString() " + findMemberByDi.get().toString());
//
//                    PaintingDtoDataResponse paintingDtoDataResponse = businessServiceClient.getSelectedPaintingList(findMemberByDi.get().getId());
//                    // 이미 회원가입 + 닉네임까지 추가 + 선호그림은 선택 x
//                    if (findMemberByDi.get().getNickname() != null && paintingDtoDataResponse.getData().toString().equals("[]")) {
//                        attributes.put("isSelectPainting", false);
//                        attributes.put("isNickname", true);
//
//                    } else if (findMemberByDi.get().getNickname() != null && !paintingDtoDataResponse.getData().toString().equals("[]")) {
//                        // 이미 회원가입 + 닉네임까지 추가 + 선호그림까지 선택 o
//                        attributes.put("isSelectPainting", true);
//                        attributes.put("isNickname", true);
//                    } else {
//                        attributes.put("isSelectPainting", false);
//                        attributes.put("isNickname", false);
//                    }
//
//                } else {
//                    memberRepository.save(Member.builder()
//                            .name(name)
//                            .kakaoProvider(provider)
//                            .kakaoProviderId(providerId)
//                            .di(di)
//                            .build());
//                    attributes.put("isSelectPainting", false);
//                    attributes.put("isNickname", false);
//                }
//            } else {
//                PaintingDtoDataResponse paintingDtoDataResponse = businessServiceClient.getSelectedPaintingList(findMember.get().getId());
//                // 이미 회원가입 + 닉네임까지 추가 + 선호그림은 선택 x
//                if (findMember.get().getNickname() != null && paintingDtoDataResponse.getData().toString().equals("[]")) {
//                    attributes.put("isSelectPainting", false);
//                    attributes.put("isNickname", true);
//                } else if (findMember.get().getNickname() != null && !paintingDtoDataResponse.getData().toString().equals("[]")) {
//                    // 이미 회원가입 + 닉네임까지 추가 + 선호그림까지 선택 o
//                    attributes.put("isSelectPainting", true);
//                    attributes.put("isNickname", true);
//                } else {
//                    attributes.put("isSelectPainting", false);
//                    attributes.put("isNickname", false);
//                }
//            }
//        }
//
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
//    }
//
//
//    public void memberWithdrawal(Long memberId) {
//        Member findMember = memberRepository.findById(memberId).orElseThrow(() -> new MemberException(MemberExceptionType.NOT_FOUND_MEMBER));
//
//        memberRepository.deleteById(findMember.getId());
//    }


//    private String makeRedirectUrl(String accessToken, Map<String, Object> attributes) {
//
//        if((boolean) attributes.get("isAddNickname") && (boolean) attributes.get("isSelectPainting")){
//            return UriComponentsBuilder.fromUriString("http://localhost:3002/auth")
//                    .queryParam("accessToken", accessToken)
//                    .queryParam("isPainting", true)
//                    .queryParam("isNickname", true)
//                    .build().toUriString();
//        }else if((boolean) attributes.get("isAddNickname") && !(boolean) attributes.get("isSelectPainting")){
//            return UriComponentsBuilder.fromUriString("http://localhost:3002/auth")
//                    .queryParam("accessToken", accessToken)
//                    .queryParam("isPainting", false)
//                    .queryParam("isNickname", true)
//                    .build().toUriString();
//        }else {
//            return UriComponentsBuilder.fromUriString("http://localhost:3002/auth")
//                    .queryParam("accessToken", accessToken)
//                    .queryParam("isPainting", false)
//                    .queryParam("isNickname", false)
//                    .build().toUriString();
//        }
//    }
}
