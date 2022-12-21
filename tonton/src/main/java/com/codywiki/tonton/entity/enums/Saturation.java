package com.codywiki.tonton.entity.enums;

import java.util.Arrays;

public enum Saturation {
    LEVEL_ZERO(0), LEVEL_ONE(1), LEVEL_TWO(2);

    private final int level;

    Saturation(final int level) {
        this.level = level;
    }

    public static Saturation findSaturation(final int level) {
        return Arrays.stream(values())
                .filter(saturation -> saturation.level == level)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 존재하지 않는 Saturation값 입니다."));
    }
}
