package com.codywiki.tonton.controller;

import com.codywiki.tonton.controller.dto.ResponseDto;
import com.codywiki.tonton.entity.enums.Color;
import com.codywiki.tonton.message.ResponseMessage;
import com.codywiki.tonton.service.ClothesService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/clothes")
public class ClothesController {
    private final ClothesService clothesService;

    @GetMapping
    public ResponseEntity<ResponseDto> getTotal(
            @PageableDefault(sort = "id", direction = Direction.DESC, size = 25) Pageable pageable) {
        return ResponseEntity.ok(ResponseDto.of(
                HttpStatus.OK,
                ResponseMessage.ALL_CLOTHES_SUCCESS,
                clothesService.findAllClothes(pageable))
        );
    }

    @GetMapping("sites/{siteName}")
    public ResponseEntity<ResponseDto> getTotalBySite(
            @PageableDefault(sort = "id", direction = Direction.DESC, size = 25) Pageable pageable,
            @PathVariable final String siteName) {
        return ResponseEntity.ok(ResponseDto.of(
                HttpStatus.OK,
                ResponseMessage.ALL_CLOTHES_BY_SITE_SUCCESS,
                clothesService.findAllClothesBySite(pageable, siteName)
        ));
    }

    @GetMapping("colors/{colorName}")
    public ResponseEntity<ResponseDto> getTotalByColor(
            @PageableDefault(sort = "id", direction = Direction.DESC, size = 25) Pageable pageable,
            @PathVariable String colorName) {
        Color color = Color.valueOf(colorName.toUpperCase());
        return ResponseEntity.ok(ResponseDto.of(
                HttpStatus.OK,
                ResponseMessage.ALL_CLOTHES_BY_COLOR_SUCCESS,
                clothesService.findAllClothesByColor(pageable, color)
        ));

    }
}
