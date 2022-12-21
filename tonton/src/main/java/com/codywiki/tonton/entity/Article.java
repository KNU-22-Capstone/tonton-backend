package com.codywiki.tonton.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

@DynamicInsert
@Entity
@NoArgsConstructor
@Getter
public class Article {
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    @Lob
    private String content;
    @ColumnDefault("0")
    private long likeCount;
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date writeDate;
    private String category;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "article")
    private final List<Coordination> coordinations = new ArrayList<>();

    @OneToMany(mappedBy = "article")
    private final List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "article")
    private final List<Recommend> recommends = new ArrayList<>();

    @Builder
    public Article(final String title, final String content, final long likeCount, final Date writeDate,
                   final String category, final Member member) {
        this.title = title;
        this.content = content;
        this.likeCount = likeCount;
        this.writeDate = writeDate;
        this.category = category;
        this.member = member;
    }
}
