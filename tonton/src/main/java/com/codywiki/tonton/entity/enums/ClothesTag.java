package com.codywiki.tonton.entity.enums;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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
            List.of(ClothesDetailTag.HAT)),
    EMPTY("없음", Collections.EMPTY_LIST);

    private final String title;
    private final List<ClothesDetailTag> detailTags;

    ClothesTag(final String title, final List<ClothesDetailTag> detailTags) {
        this.title = title;
        this.detailTags = detailTags;
    }

    public static List<ClothesTag> getTagsExcept(final ClothesTag majorTag) {
        return Arrays.stream(values())
                .filter(clothesTag -> clothesTag != majorTag)
                .collect(Collectors.toList());
    }

    public static ClothesTag findMajorTag(final String majorTag) {
        return Arrays.stream(values())
                .filter(clothesTag -> clothesTag.title.equals(majorTag))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 존재하지 않는 majorTag 입니다."));
    }
}
