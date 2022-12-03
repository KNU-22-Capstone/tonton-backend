package com.codywiki.tonton.service;

import com.codywiki.tonton.dto.jwt.TokenDto;
import com.codywiki.tonton.dto.jwt.TokenServiceDto;
import com.codywiki.tonton.dto.member.MemberResponseDto;
import com.codywiki.tonton.dto.member.sign.MemberSignInServiceDto;
import com.codywiki.tonton.dto.member.sign.MemberSignUpServiceDto;
import com.codywiki.tonton.entity.Member;
import com.codywiki.tonton.entity.jwt.RefreshToken;
import com.codywiki.tonton.jwt.TokenProvider;
import com.codywiki.tonton.repository.MemberRepository;
import com.codywiki.tonton.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.boot.model.naming.IllegalIdentifierException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;

    @Transactional
    public MemberResponseDto signUp(MemberSignUpServiceDto signUpServiceDto) {
        if (memberRepository.existsByLoginId(signUpServiceDto.getLoginId())) {
            throw new IllegalArgumentException("[ERROR] 이미 가입되어 있는 유저입니다.");
        }
        Member member = signUpServiceDto.toMember(passwordEncoder);
        return MemberResponseDto.of(memberRepository.save(member));
    }

    @Transactional
    public TokenDto signIn(MemberSignInServiceDto signInServiceDto) {
        // 1. Login ID/PW를 기반으로 AuthenticationToken 생성
        UsernamePasswordAuthenticationToken authenticationToken = signInServiceDto.toAuthentication();

        // 2. 실제로 검증(사용자 비밀번호 체크)이 이루어지는 부분
        // authenticate 메서드가 실행될때 CustomUserDetailService에서 만들었던 loadUserByUsername 메서드가 실행됨
        Authentication authentication = authenticationManagerBuilder.getObject()
                .authenticate(authenticationToken);

        // 3. 인증 정보를 기반으로 JWT 토큰 생성
        TokenDto tokenDto = tokenProvider.generateTokenDto(authentication);

        // 4. RefreshToken 저장
        RefreshToken refreshToken = RefreshToken.builder()
                .key(authentication.getName())
                .value(tokenDto.getRefreshToken())
                .build();
        refreshTokenRepository.save(refreshToken);
        return tokenDto;
    }

    @Transactional
    public TokenDto reissue(TokenServiceDto tokenServiceDto) {
        // 1. 리프레쉬 토큰 인덱스로 리프레쉬 토큰 가져오기
        RefreshToken refreshToken = refreshTokenRepository.findByKey(tokenServiceDto.getRefreshTokenIndex())
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 존재하지 않는 리프레쉬 토큰입니다."));

        // 2. Refresh Token 검증
        if (!tokenProvider.validateToken(refreshToken.getValue())) {
            throw new IllegalIdentifierException("[ERROR] Refresh Token이 유효하지 않습니다.");
        }

        // 3. Access Token에서 Member ID 가져오기
        Authentication authentication = tokenProvider.getAuthentication(tokenServiceDto.getAccessToken());
        // 4. 저장소에서 Member ID를 기반으로 Refresh Token값 가져옴
        RefreshToken refreshTokenToValidate = refreshTokenRepository.findByKey(authentication.getName())
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 로그아웃 된 사용자입니다."));

        // 4. Refresh Token 일치하는지 검사
        if (!refreshTokenToValidate.getValue().equals(refreshToken.getValue())) {
            throw new IllegalArgumentException("[ERROR] 토큰의 유저 정보가 일치하지 않습니다.");
        }

        // 5. 새로운 토큰 생성
        TokenDto tokenDto = tokenProvider.generateTokenDto(authentication);

        // 6. 저장소 정보 업데이트
        RefreshToken newRefreshToken = refreshToken.updateValue(tokenDto.getRefreshToken());
        refreshTokenRepository.save(newRefreshToken);

        // 토큰 발급
        return tokenDto;
    }

    public void signOut(final String authorization) {
        Authentication authentication = tokenProvider.getAuthentication(parseAccessToken(authorization));
        RefreshToken refreshToken = refreshTokenRepository.findByKey(authentication.getName())
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 로그아웃 된 사용자입니다."));
        refreshTokenRepository.delete(refreshToken);
    }

    private String parseAccessToken(final String accessToken) {
        return accessToken.substring(7);
    }
}
