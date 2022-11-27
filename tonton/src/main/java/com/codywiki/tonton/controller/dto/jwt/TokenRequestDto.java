package com.codywiki.tonton.controller.dto.jwt;

import com.codywiki.tonton.dto.jwt.TokenServiceDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TokenRequestDto {
    private String accessToken;
    private String refreshToken;

    public TokenServiceDto toServiceDto() {
        return TokenServiceDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}
