package com.codywiki.tonton.repository;

import com.codywiki.tonton.entity.Clothes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClothesRepository extends JpaRepository<Clothes, Long> {
    Clothes findByPictureUrl(final String pictureUrl);
}
