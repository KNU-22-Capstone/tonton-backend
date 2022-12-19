package com.codywiki.tonton.entity.enums;

public enum Color {
    R("red"), O("orange"), Y("yellow"), GA("greenA"), GB("greenB"),
    GC("greenC"), BA("blueA"), BB("blueB"), BC("blueC"), PA("purpleA"),
    PB("purpleB"), PC("purpleC"), BK("black");

    private final String name;

    Color(final String name) {
        this.name = name;
    }
}
