package com.codywiki.tonton.dto.member;

import com.codywiki.tonton.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberResponseDto {
    private String loginId;
    private String name;
    private String nickname;

    public static MemberResponseDto of(final Member member) {
        return MemberResponseDto.builder()
                .loginId(member.getLoginId())
                .name(member.getName())
                .nickname(member.getNickname())
                .build();
    }
}
