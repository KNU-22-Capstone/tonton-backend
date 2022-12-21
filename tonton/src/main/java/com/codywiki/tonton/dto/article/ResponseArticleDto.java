package com.codywiki.tonton.dto.article;

import com.codywiki.tonton.dto.member.MemberInfoDto;
import com.codywiki.tonton.entity.Article;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class ResponseArticleDto {
    private Long id;
    private String title;
    private String content;
    private long likeCount;
    private Date writeDate;
    private String category;
    private MemberInfoDto memberInfoDto;

    public static ResponseArticleDto of(final Article article) {
        return ResponseArticleDto.builder()
                .id(article.getId())
                .title(article.getTitle())
                .content(article.getContent())
                .likeCount(article.getLikeCount())
                .writeDate(article.getWriteDate())
                .category(article.getCategory())
                .memberInfoDto(MemberInfoDto.of(article.getMember()))
                .build();
    }
}
