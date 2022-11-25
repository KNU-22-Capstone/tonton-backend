package com.codywiki.tonton.service;

import com.codywiki.tonton.domain.test.Authority;
import com.codywiki.tonton.domain.test.User;
import com.codywiki.tonton.dto.UserDto;
import com.codywiki.tonton.repository.UserRepository;
import com.codywiki.tonton.utils.SecurityUtil;
import java.util.Collections;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * UserRepository와 PasswordEncoder를 주입받음
 */
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * 회원가입 로직을 수행하는 메소드이다.
     * 파라미터로 받은 UserDto를 이용해 username이 존재하지 않으면 Authority와 User정보를 생성해서
     * UserRepository의 save메소드를 통해 DB에 정보를 저장한다.
     * <p>
     * signup 메소드를 통해 가입한 회원은 ROLL_USER를 가지고 있고, data.sql에서 자동 생성되는 admin계정은 USER, ADMIN ROLE을 가지고 있다.
     * 이 차이를 통해 권한검증 부분을 테스트함
     */
    @Transactional
    public User signup(UserDto userDto) {
        if (userRepository.findOneWithAuthoritiesByUsername(userDto.getUsername())
                .orElse(null) != null) {
            throw new RuntimeException("이미 가입되어 있는 유저입니다.");
        }

        Authority authority = Authority.builder()
                .authorityName("ROLE_USER")
                .build();

        return userRepository.save(User.builder()
                .username(userDto.getUsername())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .nickname(userDto.getNickname())
                .authorities(Collections.singleton(authority))
                .activated(true)
                .build());
    }

    // 아래 두 메소드의 허용권한을 다르게해 권한검증에 대한 부분을 테스트함

    /**
     * username을 기준으로 정보를 가져옴
     */
    @Transactional(readOnly = true)
    public Optional<User> getUserWithAuthorities(String username) {
        return userRepository.findOneWithAuthoritiesByUsername(username);
    }

    /**
     * 현재 SecurityContext에 저장된 username의 정보만 가져옴
     */
    @Transactional(readOnly = true)
    public Optional<User> getMyUserWithAuthorities() {
        return SecurityUtil.getCurrentUsername()
                .flatMap(userRepository::findOneWithAuthoritiesByUsername);
    }
}
