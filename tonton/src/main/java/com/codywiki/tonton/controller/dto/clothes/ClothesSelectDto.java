package com.codywiki.tonton.controller.dto.clothes;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@AllArgsConstructor
public class ClothesSelectDto {
    @NotNull
    private final String detailTag;
    private final String color;
    private final String siteName;
}
