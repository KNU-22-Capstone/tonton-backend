package com.codywiki.tonton.dto.member.sign;

import com.codywiki.tonton.entity.Member;
import com.codywiki.tonton.entity.enums.Authority;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberSignUpServiceDto {
    private String loginId;
    private String password;
    private String name;
    private String nickname;

    public Member toMember(PasswordEncoder passwordEncoder) {
        return Member.builder()
                .loginId(loginId)
                .password(passwordEncoder.encode(password))
                .name(name)
                .nickname(nickname)
                .authority(Authority.ROLE_USER)
                .build();
    }
}
