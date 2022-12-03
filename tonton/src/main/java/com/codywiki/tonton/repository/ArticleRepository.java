package com.codywiki.tonton.repository;

import com.codywiki.tonton.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}
