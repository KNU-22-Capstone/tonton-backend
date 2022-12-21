package com.codywiki.tonton.entity.enums;

import java.util.Arrays;

public enum Value {
    LEVEL_ZERO(0), LEVEL_ONE(1), LEVEL_TWO(2), LEVEL_THREE(3);

    private final int level;

    Value(final int level) {
        this.level = level;
    }

    public static Value findValue(final int level) {
        return Arrays.stream(values())
                .filter(value -> value.level == level)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 존재하지 않는 Value값 입니다."));
    }
}
