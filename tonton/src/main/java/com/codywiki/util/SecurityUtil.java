package com.codywiki.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;


/**
 * getCurrentMemberId메소드의 역할은 SecurityContext의 Authentication 객체를 이용해 memberId 리턴해주는 간단한 유틸성 메소드
 * <p>
 * SecurityContext에 Authentication객체는 JwtFilter의 doFilter메소드에서 Request가 들어올때
 * SecurityContext에 Authentication 객체를 저장해서 사용하게 된다.
 */
@Slf4j
public class SecurityUtil {

    private SecurityUtil() {
    }

    // SecurityContext 에 유저 정보가 저장되는 시점
    // Request 가 들어올 때 JwtFilter 의 doFilter 에서 저장
    public static Long getCurrentMemberId() {
        final Authentication authentication = SecurityContextHolder.getContext()
                .getAuthentication();

        if (authentication == null || authentication.getName() == null) {
            throw new IllegalArgumentException("[ERROR] Security Context 에 인증 정보가 없습니다.");
        }

        return Long.parseLong(authentication.getName());
    }
}