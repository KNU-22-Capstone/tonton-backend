package com.codywiki.tonton.repository;

import com.codywiki.tonton.entity.Clothes;
import com.codywiki.tonton.entity.enums.ClothesDetailTag;
import com.codywiki.tonton.entity.enums.ClothesTag;
import com.codywiki.tonton.entity.enums.Color;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClothesRepository extends JpaRepository<Clothes, Long> {
    Clothes findByPictureUrl(final String pictureUrl);

    Page<Clothes> findAllByDetailTag(final Pageable pageable, final ClothesDetailTag detailTag);

    Page<Clothes> findAllByDetailTagAndColor(
            final Pageable pageable,
            final ClothesDetailTag detailTag,
            final Color color
    );

    Page<Clothes> findAllByDetailTagAndSiteName(Pageable pageable, ClothesDetailTag detailTag, String siteName);

    Page<Clothes> findAllByDetailTagAndColorAndSiteName(
            Pageable pageable,
            ClothesDetailTag detailTag,
            Color color,
            String siteName);

    /**
     * 1. 입력받은 의류태그 제외 나머지 태그에서 10개씩 추출 2. 현재 색상에서 찾아야함 (Tone In Tone)
     */
    List<Clothes> findTop10ByMajorTagAndColor(final ClothesTag majorTag, final Color color);
}
