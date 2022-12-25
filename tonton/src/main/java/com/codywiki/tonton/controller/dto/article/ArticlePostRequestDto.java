package com.codywiki.tonton.controller.dto.article;

import com.codywiki.tonton.dto.article.ArticlePostServiceDto;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ArticlePostRequestDto {
    private String title;
    private String content;
    private Date writeDate;
    private List<String> imageUrls;

    public ArticlePostServiceDto toService() {
        return ArticlePostServiceDto.builder()
                .title(title)
                .content(content)
                .writeDate(writeDate)
                .imageUrls(imageUrls)
                .build();
    }
}
