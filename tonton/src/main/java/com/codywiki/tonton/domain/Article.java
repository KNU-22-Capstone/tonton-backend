package com.codywiki.tonton.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class Article {
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String content;
    private long likeCount;
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
