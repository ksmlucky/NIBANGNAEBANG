package com.footprints.apigatewayservice.jwt;

import com.footprints.apigatewayservice.exception.TokenException;
import com.footprints.apigatewayservice.exception.TokenExceptionType;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
@Slf4j
public class TokenProvider implements InitializingBean {

    private final String secret;


    private Key key;

    public TokenProvider(
            @Value("${jwt.secret}") String secret) {
        this.secret = secret;
    }

    // DI받은 secret키를 복호화하여 key변수에 할당
    @Override
    public void afterPropertiesSet() {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    // 토큰에서 회원 정보 추출
    public String getMemberProviderId(String token) {
        try {
            if (token == null || token.isEmpty()) {
                log.info("TokenException {}", TokenExceptionType.NOT_FOUND_TOKEN.getErrorMessage());
                throw new TokenException(TokenExceptionType.NOT_FOUND_TOKEN);
            }
            return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().get("id").toString();

        }
        catch (TokenException e) {
            log.info("TokenException {}",TokenExceptionType.NOT_FOUND_TOKEN.getErrorMessage());
            throw new TokenException(TokenExceptionType.NOT_FOUND_TOKEN);
        }
        catch (ExpiredJwtException e) {
            throw new ExpiredJwtException(e.getHeader(), e.getClaims(), e.getMessage());
        } catch (SignatureException e) {
            throw new SignatureException(e.getMessage());
        } catch (MalformedJwtException e) {
            throw new MalformedJwtException(e.getMessage());
        }
    }

    // 토큰을 파싱하고 발생하는 예외를 처리, 문제가 있을 경우 false 반환
    public boolean validateToken(String token) {
        try {
            // 토큰 파싱하고 만약 예외가 발생하면 캐치   `
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            log.info("잘못된 JWT 서명입니다.");
        } catch (ExpiredJwtException e) {
            log.info("만료된 JWT 토큰입니다.");
        } catch (UnsupportedJwtException e) {
            log.info("지원되지 않는 JWT 토큰입니다.");
        } catch (IllegalArgumentException e) {
            log.info("JWT 토큰이 잘못되었습니다.");
        }
        return false;
    }
}