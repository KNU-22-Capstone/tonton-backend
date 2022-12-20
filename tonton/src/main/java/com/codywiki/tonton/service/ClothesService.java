package com.codywiki.tonton.service;

import com.codywiki.tonton.entity.Clothes;
import com.codywiki.tonton.entity.enums.Color;
import com.codywiki.tonton.repository.ClothesRepository;
import java.util.List;
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
    public Page<Clothes> findAllClothes(Pageable pageable) {
        return clothesRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Page<Clothes> findAllClothesBySite(final Pageable pageable, final String siteName) {
        return clothesRepository.findAllBySiteName(pageable, siteName);
    }

    public Page<Clothes> findAllClothesByColor(final Pageable pageable, final Color color) {
        return clothesRepository.findAllByColor(pageable, color);
    }
}
