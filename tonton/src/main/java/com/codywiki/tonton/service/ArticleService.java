package com.codywiki.tonton.service;

import com.codywiki.tonton.dto.article.AllArticleServiceDto;
import com.codywiki.tonton.dto.article.ArticlePostServiceDto;
import com.codywiki.tonton.entity.Article;
import com.codywiki.tonton.entity.Clothes;
import com.codywiki.tonton.entity.Coordination;
import com.codywiki.tonton.entity.Member;
import com.codywiki.tonton.jwt.TokenProvider;
import com.codywiki.tonton.repository.ArticleRepository;
import com.codywiki.tonton.repository.ClothesRepository;
import com.codywiki.tonton.repository.CoordinationRepository;
import com.codywiki.tonton.repository.MemberRepository;
import com.codywiki.tonton.util.SecurityUtil;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final CoordinationRepository coordinationRepository;
    private final ClothesRepository clothesRepository;
    private final MemberRepository memberRepository;


    public void save(final ArticlePostServiceDto writeArticle) {
        Member findMember = memberRepository.findById(SecurityUtil.getCurrentMemberId())
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 존재하지 않는 사용자 입니다."));
        List<Clothes> allClothes = writeArticle.getImageUrls()
                .stream()
                .map(clothesRepository::findByPictureUrl)
                .collect(Collectors.toList());
        Article saveArticle = saveArticle(writeArticle, findMember);
        allClothes.forEach(clothes -> coordinationRepository.save(
                Coordination.builder()
                        .clothes(clothes)
                        .article(saveArticle)
                        .build()
        ));
    }

    private Article saveArticle(final ArticlePostServiceDto writeArticle, final Member findMember) {
        return articleRepository.save(Article.builder()
                .member(findMember)
                .title(writeArticle.getTitle())
                .content(writeArticle.getContent())
                .writeDate(writeArticle.getWriteDate())
                .build());
    }

    public AllArticleServiceDto readAll() {
        return new AllArticleServiceDto(articleRepository.findAll());
    }
}
