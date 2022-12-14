package com.codywiki.tonton.entity.enums;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Color {
    R("red"), O("orange"), Y("yellow"), GA("greenA"), GB("greenB"),
    GC("greenC"), BA("blueA"), BB("blueB"), BC("blueC"), PA("purpleA"),
    PB("purpleB"), PC("purpleC"), BK("black");

    private final String name;

    Color(final String name) {
        this.name = name;
    }

    public static List<Color> getColorsExcept(final Color inputColor) {
        return Arrays.stream(values())
                .filter(color -> color != inputColor)
                .collect(Collectors.toList());
    }
}
