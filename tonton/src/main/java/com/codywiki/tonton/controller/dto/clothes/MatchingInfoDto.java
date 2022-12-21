package com.codywiki.tonton.controller.dto.clothes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@AllArgsConstructor
public class MatchingInfoDto {
    private final String majorTag;
    private final String color;
    private final int saturation;
    private final int value;
}
