package com.codywiki.tonton.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.codywiki.tonton.entity.Member;
import org.aspectj.lang.annotation.After;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @After("")
    void clean() {
        memberRepository.deleteAll();
    }

    @DisplayName("멤버 정상 등록 테스트")
    @Test
    void saveMember() {
        // given
        Member member = Member.builder()
                .name("이호석")
                .nickname("hoseok")
                .loginId("lee123")
                .password("1234")
                .build();

        // when
        Member saveMember = memberRepository.save(member);

        // then
        Assertions.assertAll(() ->
        {
            assertThat(member.getId()).isEqualTo(saveMember.getId());
            assertThat(member.getName()).isEqualTo(saveMember.getName());
        });
    }
}