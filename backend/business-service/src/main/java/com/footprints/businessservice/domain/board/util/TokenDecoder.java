package com.footprints.businessservice.domain.board.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;

@Slf4j
@Component
public class TokenDecoder {
    @Value("${jwt.secret}")
    private String secret;
    private Key key;

    public void afterPropertiesSet() {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    public Long extractMember(String token) {
        afterPropertiesSet();

        // token id값 추출
        Long memberId = Long.parseLong(
                Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().get("id")
                        .toString());

        log.debug("memberId -> {}", memberId);
        return memberId;
    }

}
