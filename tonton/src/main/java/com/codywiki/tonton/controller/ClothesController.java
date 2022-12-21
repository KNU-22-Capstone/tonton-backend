package com.codywiki.tonton.controller;

import com.codywiki.tonton.controller.dto.ResponseDto;
import com.codywiki.tonton.controller.dto.clothes.ClothesSelectDto;
import com.codywiki.tonton.message.ResponseMessage;
import com.codywiki.tonton.service.ClothesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/clothes")
public class ClothesController {
    private final ClothesService clothesService;

    @GetMapping
    public ResponseEntity<ResponseDto> getTotal(
            @PageableDefault(sort = "id", direction = Direction.DESC, size = 25) Pageable pageable,
            @ModelAttribute ClothesSelectDto clothesSelectDto) {
        log.info("clothesSelectDto = {}", clothesSelectDto);
        return ResponseEntity.ok(ResponseDto.of(
                HttpStatus.OK,
                ResponseMessage.ALL_CLOTHES_SUCCESS,
                clothesService.findAllClothes(pageable, clothesSelectDto)
        ));
    }
}
