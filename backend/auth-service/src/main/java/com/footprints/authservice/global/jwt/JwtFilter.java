package com.footprints.authservice.global.jwt;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class JwtFilter extends GenericFilterBean {

    public static final String AUTHORIZATION_HEADER = "Authorization";
    private final TokenProvider tokenProvider;

    // 토큰의 인증정보를 SecurityContext에 저장하는 역할
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        // 헤더에 있는 토큰 파싱
        String jwt = resolveToken(httpServletRequest);
        String requestURI = httpServletRequest.getRequestURI();
        try {
            // 토큰의 유효성 검증
            if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {
                // 토큰이 정상적이면 토큰에서 Authentication 객체를 가져옴
                Authentication authentication = tokenProvider.getAuthentication(jwt);
                // 해당 Authentication 객체를 SecurityContext에 저장
                SecurityContextHolder.getContext().setAuthentication(authentication);
                log.info("Security Context에 '{}' 인증 정보를 저장했습니다, uri: {}", authentication.getName(), requestURI);
            } else {
                servletRequest.setAttribute("exception", "유효한 JWT 토큰이 없습니다.");
                log.info("유효한 JWT 토큰이 없습니다., uri: {}", requestURI);
            }
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            servletRequest.setAttribute("exception", "잘못된 JWT 서명입니다.");
            log.info("잘못된 JWT 서명입니다.");
        } catch (ExpiredJwtException e) {
            servletRequest.setAttribute("exception", "만료된 JWT 토큰입니다.");
            log.info("만료된 JWT 토큰입니다.");
        } catch (UnsupportedJwtException e) {
            servletRequest.setAttribute("exception", "지원되지 않는 JWT 토큰입니다.");
            log.info("지원되지 않는 JWT 토큰입니다.");
        } catch (IllegalArgumentException e) {
            servletRequest.setAttribute("exception", "JWT 토큰이 잘못되었습니다.");
            log.info("JWT 토큰이 잘못되었습니다.");
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    // request에서 헤더에 있는 토큰 정보를 파싱
    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}