package com.codywiki.tonton.utils;

import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

@Slf4j
public class SecurityUtil {
    private SecurityUtil() {
    }

    /**
     * getCurrentUsername메소드의 역할은 SecurityContext의 Authentication 객체를 이용해 username을 리턴해주는 간단한 유틸성 메소드
     * <p>
     * SecurityContext에 Authentication객체는 JwtFilter의 doFilter메소드에서 Request가 들어올때
     * SecurityContext에 Authentication 객체를 저장해서 사용하게 된다.
     */
    public static Optional<String> getCurrentUsername() {
        final Authentication authentication = SecurityContextHolder.getContext()
                .getAuthentication();

        if (authentication == null) {
            log.debug("Security Context에 인증 정보가 없습니다.");
            return Optional.empty();
        }

        String username;
        if (authentication.getPrincipal() instanceof UserDetails) {
            UserDetails springSecurityUser = (UserDetails) authentication.getPrincipal();
            username = springSecurityUser.getUsername();
            return Optional.ofNullable(username);
        }
        if (authentication.getPrincipal() instanceof String) {
            return Optional.ofNullable((String) authentication.getPrincipal());
        }
        return Optional.empty();
    }
}

