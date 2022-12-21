package com.codywiki.tonton.service;

import com.codywiki.tonton.controller.dto.clothes.ClothesSelectDto;
import com.codywiki.tonton.entity.Clothes;
import com.codywiki.tonton.entity.enums.ClothesDetailTag;
import com.codywiki.tonton.entity.enums.Color;
import com.codywiki.tonton.repository.ClothesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ClothesService {
    private final ClothesRepository clothesRepository;

    @Transactional(readOnly = true)
    public Page<Clothes> findAllClothes(final Pageable pageable, final ClothesSelectDto clothesSelectDto) {
        return getClothes(pageable, clothesSelectDto);
    }

    private Page<Clothes> getClothes(final Pageable pageable, final ClothesSelectDto clothesSelectDto) {
        ClothesDetailTag detailTag = ClothesDetailTag.getTag(clothesSelectDto.getDetailTag());
        Color color = getColor(clothesSelectDto);

        if (color == null && clothesSelectDto.getSiteName() == null) {
            return clothesRepository.findAllByDetailTag(pageable, detailTag);
        }
        if (color == null) {
            return clothesRepository.findAllByDetailTagAndSiteName(pageable, detailTag, clothesSelectDto.getSiteName());
        }
        if (clothesSelectDto.getSiteName() == null) {
            return clothesRepository.findAllByDetailTagAndColor(pageable, detailTag, color);
        }
        return clothesRepository.findAllByDetailTagAndColorAndSiteName(pageable, detailTag, color,
                clothesSelectDto.getSiteName());
    }


    private static Color getColor(final ClothesSelectDto clothesSelectDto) {
        if (clothesSelectDto.getColor() != null) {
            return Color.valueOf(clothesSelectDto.getColor());
        }
        return null;
    }

    @Transactional(readOnly = true)
    public Page<Clothes> findAllClothesBySite(final Pageable pageable, final String siteName) {
        return clothesRepository.findAllBySiteName(pageable, siteName);
    }

    public Page<Clothes> findAllClothesByColor(final Pageable pageable, final Color color) {
        return clothesRepository.findAllByColor(pageable, color);
    }
}
