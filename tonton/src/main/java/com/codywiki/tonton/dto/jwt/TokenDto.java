package com.codywiki.tonton.dto.jwt;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TokenDto {
    private String refreshTokenIndex;
    private String grantType;
    private String accessToken;
    @JsonIgnore
    private String refreshToken;
    private Long accessTokenExpiresIn;
}
