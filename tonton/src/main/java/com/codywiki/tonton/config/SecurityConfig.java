package com.codywiki.tonton.config;

import com.codywiki.tonton.jwt.JwtAccessDeniedHandler;
import com.codywiki.tonton.jwt.JwtAuthenticationEntryPoint;
import com.codywiki.tonton.jwt.JwtSecurityConfig;
import com.codywiki.tonton.jwt.TokenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) // @PreAuthorize라는 어노테이션을 메소드 단위로 사용하기 위함
public class SecurityConfig {
    private final TokenProvider tokenProvider;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

    public SecurityConfig(final TokenProvider tokenProvider,
                          final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint,
                          final JwtAccessDeniedHandler jwtAccessDeniedHandler) {
        this.tokenProvider = tokenProvider;
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
        this.jwtAccessDeniedHandler = jwtAccessDeniedHandler;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web
                .ignoring()
                .antMatchers("/favicon.ico");
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccessDeniedHandler)

                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 세션을 사용하지 않으므로

                .and()
                .authorizeRequests()
                .antMatchers("/api/hello") // 토큰을 받는 로그인 api, 회원가입 api는 토큰이 없는 상태에서 요청이 들어오기 때문에 열어줌
                .permitAll()
                .antMatchers("/api/authenticate")
                .permitAll()
                .antMatchers("/api/signup")
                .permitAll()
                .anyRequest()
                .authenticated()

                .and()
                .apply(new JwtSecurityConfig(tokenProvider));   // JwtFiler를 addFilterBefore로 등록했던 JwtSecurityConfig 클래스도 적용함

        return http.build();
    }
}
