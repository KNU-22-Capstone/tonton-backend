package com.codywiki.tonton.service;

import com.codywiki.tonton.controller.dto.clothes.ClothesSelectDto;
import com.codywiki.tonton.controller.dto.clothes.MatchingInfoDto;
import com.codywiki.tonton.dto.clothes.MatchingInfoServiceDto;
import com.codywiki.tonton.entity.Clothes;
import com.codywiki.tonton.entity.enums.ClothesDetailTag;
import com.codywiki.tonton.entity.enums.ClothesTag;
import com.codywiki.tonton.entity.enums.Color;
import com.codywiki.tonton.repository.ClothesRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
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


    private Color getColor(final ClothesSelectDto clothesSelectDto) {
        if (clothesSelectDto.getColor() != null) {
            return Color.valueOf(clothesSelectDto.getColor());
        }
        return null;
    }

    public void findMatchingResult(final MatchingInfoDto matchingResultDto) {
        MatchingInfoServiceDto matchingInfoServiceDto = MatchingInfoServiceDto.of(matchingResultDto);
        findToneInTone(matchingInfoServiceDto.getMajorTag(), matchingInfoServiceDto.getColor());
        findToneAndTone(matchingInfoServiceDto);
    }

    private List<Clothes> findToneAndTone(final MatchingInfoServiceDto matchingInfoServiceDto) {
        return null;
    }

    private List<Clothes> findToneInTone(final ClothesTag majorTag, final Color color) {
        List<ClothesTag> tagsToFind = ClothesTag.getTagsExcept(majorTag);
        log.info("tagsToFind = {}", tagsToFind);
        return null;
    }
}
