package com.codywiki.tonton.dto.article;

import com.codywiki.tonton.entity.Article;
import java.util.List;

public class AllArticleServiceDto {
    private final List<Article> articles;

    public AllArticleServiceDto(final List<Article> articles) {
        this.articles = articles;
    }
}
