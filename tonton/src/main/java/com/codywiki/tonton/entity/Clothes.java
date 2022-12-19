package com.codywiki.tonton.entity;

import com.codywiki.tonton.entity.enums.ClothesDetailTag;
import com.codywiki.tonton.entity.enums.ClothesTag;
import com.codywiki.tonton.entity.enums.Color;
import com.codywiki.tonton.entity.enums.Saturation;
import com.codywiki.tonton.entity.enums.Value;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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

    @Column(unique = true)
    private String name;

    private int price;

    @Enumerated(EnumType.STRING)
    private ClothesTag majorTag;

    @Enumerated(EnumType.STRING)
    private ClothesDetailTag detailTag;

    @Enumerated(EnumType.STRING)
    private Color color;

    @Enumerated(EnumType.ORDINAL)
    private Saturation saturation;

    @Enumerated(EnumType.ORDINAL)
    private Value value;

    @Column(unique = true)
    private String pictureUrl;
    private String siteUrl;
    private String siteName;
    private Long views;
    private boolean sold;

    @OneToMany(mappedBy = "clothes")
    @JsonIgnore
    private final List<Coordination> coordinations = new ArrayList<>();

    @OneToMany(mappedBy = "clothes")
    @JsonIgnore
    private final List<Zzim> zzims = new ArrayList<>();

    @Builder
    public Clothes(final String name, final int price, final ClothesTag majorTag,
                   final ClothesDetailTag detailTag, final Color color,
                   final Saturation saturation, final Value value, final String pictureUrl, final String siteUrl,
                   final String siteName, final Long views,
                   final boolean sold) {
        this.name = name;
        this.price = price;
        this.majorTag = majorTag;
        this.detailTag = detailTag;
        this.color = color;
        this.saturation = saturation;
        this.value = value;
        this.pictureUrl = pictureUrl;
        this.siteUrl = siteUrl;
        this.siteName = siteName;
        this.views = views;
        this.sold = sold;
    }
}
