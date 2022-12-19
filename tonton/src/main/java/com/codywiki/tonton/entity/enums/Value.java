package com.codywiki.tonton.entity.enums;

public enum Value {
    LEVEL_ZERO(0), LEVEL_ONE(1), LEVEL_TWO(2), LEVEL_THREE(3), LEVEL_FOUR(4);

    private final int level;

    Value(final int level) {
        this.level = level;
    }
}
