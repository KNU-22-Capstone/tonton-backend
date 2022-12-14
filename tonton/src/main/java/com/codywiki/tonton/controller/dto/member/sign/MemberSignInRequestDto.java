package com.codywiki.tonton.controller.dto.member.sign;

import com.codywiki.tonton.dto.member.sign.MemberSignInServiceDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberSignInRequestDto {

    @NotNull
    private String loginId;

    @NotNull
    @JsonProperty(access = Access.WRITE_ONLY)
    private String password;

    public MemberSignInServiceDto toServiceDto() {
        return MemberSignInServiceDto.builder()
                .loginId(loginId)
                .password(password)
                .build();
    }
}
