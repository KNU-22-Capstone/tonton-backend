package com.codywiki.tonton.jwt;

import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * TokenProvider, JwtFilter를 SecurityConfig에 적용할때 사용할 JwtSecurityConfig 클래스
 * <P>
 * SecurityConfigurerAdapter를 extends하고 TokenPrivider를 주입받아서 JwtFilter를 통해 Security로직에 필터를 등록한다.
 */
public class JwtSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
    private TokenProvider tokenProvider;

    public JwtSecurityConfig(TokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    @Override
    public void configure(final HttpSecurity http) {
        JwtFilter customJwtFilter = new JwtFilter(tokenProvider);
        http.addFilterBefore(customJwtFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
