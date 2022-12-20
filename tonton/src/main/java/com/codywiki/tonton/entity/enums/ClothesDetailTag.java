package com.codywiki.tonton.entity.enums;

import java.util.Arrays;

public enum ClothesDetailTag {
    SHORT_SLEEVE("반팔티"),
    SHIRTS("셔츠"),
    LONG_SLEEVE("긴팔티"),
    HOODIE("후드티"),
    SHORTS("반바지"),
    JEANS("청바지"),
    SLACKS("슬랙스"),
    COTTON_PANTS("면바지"),
    COAT("코트"),
    JACKET("자켓"),
    CARDIGAN("가디건"),
    SNEAKERS("스니커즈"),
    DRESS_SHOES("구두"),
    HAT("모자"),
    ETC("기타");

    private String title;

    ClothesDetailTag(final String title) {
        this.title = title;
    }

    public static ClothesDetailTag getTag(final String detailTag) {
        return Arrays.stream(values())
                .filter(tag -> tag.title.equals(detailTag))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 존재하지 않는 카테고리 입니다."));
    }

    public String getTitle() {
        return title;
    }
}
