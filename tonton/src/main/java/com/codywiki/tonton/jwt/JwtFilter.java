package com.codywiki.tonton.jwt;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

/**
 * JWT를 위한 커스텀 필터를 만들기 위해 JwtFilter 클래스 생성
 */
@Slf4j
public class JwtFilter extends GenericFilterBean {
    public static final String AUTHORIZATION_HEADER = "Authorization";

    private TokenProvider tokenProvider;

    public JwtFilter(final TokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }


    /**
     * 실제 필터링 로직이 실행됨 JWT 토큰의 인증정보를 현재 SecurityContext에 저장하는 역할 수행
     * <p>
     * request에서 JWT 토큰을 받아서 만들었던 유효성 검증을 하고, 정상 토큰이라면 Authentication 객체를 받아와 SecurityContext에 저장한다.
     */
    @Override
    public void doFilter(final ServletRequest servletRequest, final ServletResponse servletResponse, final FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String jwt = resolveToken(httpServletRequest);
        String requestURI = httpServletRequest.getRequestURI();

        if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {
            Authentication authentication = tokenProvider.getAuthentication(jwt);
            SecurityContextHolder.getContext()
                    .setAuthentication(authentication);
            log.debug("Security Context에 '{}' 인증 정보를 저장했습니다, uri: {}", authentication.getName(), requestURI);
            return;
        }
        log.debug("유효한 JWT 토큰이 없습니다, uriL {}", requestURI);

        filterChain.doFilter(servletRequest, servletResponse);
    }

    /**
     * Request Header에서 토큰 정보를 꺼내오기 위한 resolveToken 메소드 추가
     */
    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
