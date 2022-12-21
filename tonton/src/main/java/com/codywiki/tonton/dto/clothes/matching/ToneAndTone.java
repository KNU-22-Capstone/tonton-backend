package com.codywiki.tonton.dto.clothes.matching;

import com.codywiki.tonton.entity.Clothes;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ToneAndTone {
    private final List<Clothes> clothes;
}
