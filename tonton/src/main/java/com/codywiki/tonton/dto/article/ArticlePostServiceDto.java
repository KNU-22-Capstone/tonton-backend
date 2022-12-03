package com.codywiki.tonton.dto.article;

import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArticlePostServiceDto {
    private String title;
    private String content;
    private Date writeDate;
    private List<String> imageUrls;
}
