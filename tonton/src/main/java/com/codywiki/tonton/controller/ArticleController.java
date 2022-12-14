package com.codywiki.tonton.controller;

import com.codywiki.tonton.controller.dto.ResponseDto;
import com.codywiki.tonton.controller.dto.article.ArticlePostRequestDto;
import com.codywiki.tonton.message.ResponseMessage;
import com.codywiki.tonton.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/articles")
public class ArticleController {
    private final ArticleService articleService;

    @PostMapping
    public ResponseEntity<ResponseDto> save(@RequestBody final ArticlePostRequestDto writeArticle) {
        articleService.save(writeArticle.toService());
        return ResponseEntity.ok(ResponseDto.of(
                HttpStatus.OK,
                ResponseMessage.POST_ARTICLE_SUCCESS,
                null
        ));
    }

    @GetMapping
    public ResponseEntity<ResponseDto> readAll(
            @PageableDefault(sort = "id", direction = Direction.DESC) Pageable pageable) {
        return ResponseEntity.ok(ResponseDto.of(
                HttpStatus.OK,
                ResponseMessage.READ_ALL_ARTICLES_SUCCESS,
                articleService.readAll(pageable)
        ));
    }

}
