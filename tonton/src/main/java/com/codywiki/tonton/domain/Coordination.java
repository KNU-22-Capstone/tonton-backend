package com.codywiki.tonton.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class Coordination {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "clothes_id")
    private Clothes clothes;

    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;

    @Builder
    public Coordination(final Clothes clothes, final Article article) {
        this.clothes = clothes;
        this.article = article;
    }
}
