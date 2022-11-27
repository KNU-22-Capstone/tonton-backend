package com.codywiki.tonton.service;

import com.codywiki.tonton.dto.member.MemberResponseDto;
import com.codywiki.tonton.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberResponseDto findMemberInfoById(Long memberId) {
        return memberRepository.findById(memberId)
                .map(MemberResponseDto::of)
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 로그인 유저 정보가 없습니다."));
    }

    public MemberResponseDto findMemberInfoByLoginId(String loginId) {
        return memberRepository.findByLoginId(loginId)
                .map(MemberResponseDto::of)
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 유저 정보가 없습니다."));
    }
}
