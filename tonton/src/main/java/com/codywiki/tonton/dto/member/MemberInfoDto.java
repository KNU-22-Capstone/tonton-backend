package com.codywiki.tonton.dto.member;

import com.codywiki.tonton.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class MemberInfoDto {
    private String name;
    private String nickname;
    private String loginId;

    public static MemberInfoDto of(final Member member) {
        return MemberInfoDto.builder()
                .name(member.getName())
                .nickname(member.getNickname())
                .loginId(member.getLoginId())
                .build();
    }
}
