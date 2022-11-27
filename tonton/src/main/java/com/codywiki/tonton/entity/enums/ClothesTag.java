package com.codywiki.tonton.entity.enums;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum ClothesTag {
    TOP(
            "상의",
            Arrays.asList(ClothesDetailTag.SHORT_SLEEVE,
                    ClothesDetailTag.SHIRTS,
                    ClothesDetailTag.LONG_SLEEVE,
                    ClothesDetailTag.HOODIE)),
    BOTTOM(
            "하의",
            Arrays.asList(ClothesDetailTag.SHORTS,
                    ClothesDetailTag.JEANS,
                    ClothesDetailTag.SLACKS,
                    ClothesDetailTag.COTTON_PANTS)),
    OUTER("아우터",
            Arrays.asList(ClothesDetailTag.COAT,
                    ClothesDetailTag.JACKET,
                    ClothesDetailTag.CARDIGAN)),
    SHOES("신발",
            Arrays.asList(ClothesDetailTag.SNEAKERS,
                    ClothesDetailTag.DRESS_SHOES)),
    HAT("모자",
            Arrays.asList(ClothesDetailTag.HAT)),
    EMPTY("없음", Collections.EMPTY_LIST);

    private final String title;
    private final List<ClothesDetailTag> detailTags;

    ClothesTag(final String title, final List<ClothesDetailTag> detailTags) {
        this.title = title;
        this.detailTags = detailTags;
    }

    public static ClothesTag findByDetailTagType(ClothesDetailTag clothesDetailTag) {
        return Arrays.stream(ClothesTag.values())
                .filter(clothesTag -> clothesTag.hasDetailTag(clothesDetailTag))
                .findAny()
                .orElse(EMPTY);
    }

    private boolean hasDetailTag(final ClothesDetailTag clothesDetailTag) {
        return detailTags.stream()
                .anyMatch(tag -> tag == clothesDetailTag);
    }

    public String getTitle() {
        return title;
    }
}
