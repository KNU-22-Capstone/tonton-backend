package com.codywiki.tonton.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class Clothes {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private int price;
    private String majorTag;
    private String detailTag;
    private String pictureUrl;
    private String siteUrl;
    private String siteName;
    private Long views;
    private boolean sold;

    @OneToMany(mappedBy = "clothes")
    private final List<Coordination> coordinations = new ArrayList<>();

    @OneToMany(mappedBy = "clothes")
    private final List<Zzim> zzims = new ArrayList<>();

    @Builder
    public Clothes(final String name, final int price, final String majorTag, final String detailTag,
                   final String pictureUrl, final String siteUrl,
                   final String siteName, final Long views, final boolean sold) {
        this.name = name;
        this.price = price;
        this.majorTag = majorTag;
        this.detailTag = detailTag;
        this.pictureUrl = pictureUrl;
        this.siteUrl = siteUrl;
        this.siteName = siteName;
        this.views = views;
        this.sold = sold;
    }
}
