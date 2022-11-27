package com.codywiki.tonton.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.codywiki.tonton.dto.member.MemberResponseDto;
import com.codywiki.tonton.dto.member.sign.MemberSignUpServiceDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class AuthServiceTest {

    @Autowired
    private AuthService authService;

    @DisplayName("회원가입시 정상적인 ResponseDto 반환 테스트")
    @Transactional
    @Test
    void signUp() {
        // given
        MemberSignUpServiceDto member = MemberSignUpServiceDto.builder()
                .loginId("eee")
                .password("1234")
                .name("이호석")
                .nickname("이호석")
                .build();
        // when
        MemberResponseDto storedMember = authService.signUp(member);

        // then
        Assertions.assertAll(() -> {
                    assertThat(storedMember.getLoginId()).isEqualTo(member.getLoginId());
                    assertThat(storedMember.getName()).isEqualTo(member.getName());
                    assertThat(storedMember.getNickname()).isEqualTo(member.getNickname());
                }
        );
    }
}