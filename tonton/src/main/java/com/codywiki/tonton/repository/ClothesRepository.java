package com.codywiki.tonton.repository;

import com.codywiki.tonton.entity.Clothes;
import com.codywiki.tonton.entity.enums.ClothesDetailTag;
import com.codywiki.tonton.entity.enums.Color;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClothesRepository extends JpaRepository<Clothes, Long> {
    Clothes findByPictureUrl(final String pictureUrl);

    Page<Clothes> findAllBySiteName(final Pageable pageable, final String siteName);

    Page<Clothes> findAllByColor(final Pageable pageable, final Color color);

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
}
