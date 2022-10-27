package com.footprints.authservice.global.jwt;

import com.footprints.authservice.domain.Member;
import com.footprints.authservice.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service("userDetailService")
@Slf4j
public class CustomUserDetailService implements UserDetailsService {

    private final MemberRepository memberRepository;

    // username이 DB에 있는지 확인
    @Override
    public UserDetails loadUserByUsername(String memberId) throws UsernameNotFoundException {
        Member findMember = memberRepository.findById(Long.parseLong(memberId))
                .orElseThrow(() -> new RuntimeException("멤버익셉션 만들자"));

        return CustomUserDetails.builder()
                .id(String.valueOf(findMember.getId()))
                .name(findMember.getName())
                .build();
    }
}