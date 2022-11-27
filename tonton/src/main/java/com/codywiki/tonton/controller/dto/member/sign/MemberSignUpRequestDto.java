package com.codywiki.tonton.controller.dto.member.sign;

import com.codywiki.tonton.dto.member.sign.MemberSignUpServiceDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberSignUpRequestDto {

    @NotNull
    private String loginId;

    @NotNull
    @JsonProperty(access = Access.WRITE_ONLY)
    private String password;

    @NotNull
    private String name;

    @NotNull
    private String nickname;

    public MemberSignUpServiceDto toServiceDto() {
        return MemberSignUpServiceDto.builder()
                .loginId(loginId)
                .password(password)
                .name(name)
                .nickname(nickname)
                .build();
    }

}
