package com.codywiki.tonton.entity;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class Comment {
    @Id
    @GeneratedValue
    private Long id;
    private String content;
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date writeDate;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;

    @Builder
    public Comment(final String content, final Date writeDate, final Member member,
                   final Article article) {
        this.content = content;
        this.writeDate = writeDate;
        this.member = member;
        this.article = article;
    }
}
