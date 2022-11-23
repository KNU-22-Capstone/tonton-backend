package com.codywiki.tonton.controller;

import com.codywiki.tonton.dto.LoginDto;
import com.codywiki.tonton.dto.TokenDto;
import com.codywiki.tonton.jwt.JwtFilter;
import com.codywiki.tonton.jwt.TokenProvider;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 로그인 API를 추가하기 위한 클래스
 * TokenProvider, AuthenticationManagerBuilder를 주입받음
 * 로그인 API: /api/authenticate이고 POST 요청을 받음
 * <P>
 * LoginDto의 username, password를 파라미터로 받아 UsernamePasswordAuthenticationToken을 생성
 * authenticationToken을 이용해 Authentication객체를 생성하기위해 authenticate(authenticationToken)이 실행될때
 * CustomUserDetailsService의 loadUserByUsername 메소드가 실행되고 생성된 객체를 SecurityContext에 저장하고
 * Authentication 객체를 createToken 메소드를 통해서 JWT Token을 생성한다.
 * <P>
 * JWT Token을 Response Header에도 넣고, TokenDto를 이용해서 Response Body에도 넣어서 리턴한다.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("api")
public class AuthController {
    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    @PostMapping("/authenticate")
    public ResponseEntity<TokenDto> authorize(@Valid @RequestBody LoginDto loginDto) {

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());

        Authentication authentication =  authenticationManagerBuilder.getObject()
                .authenticate(authenticationToken);

        String jwt = tokenProvider.createToken(authentication);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);

        return new ResponseEntity<>(new TokenDto(jwt), httpHeaders, HttpStatus.OK);

    }
}
