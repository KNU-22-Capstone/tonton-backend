package com.codywiki.tonton.dto.jwt;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TokenServiceDto {
    private String accessToken;
    private String refreshTokenIndex;
}
