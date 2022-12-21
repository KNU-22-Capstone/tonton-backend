package com.codywiki.tonton.dto.clothes.matching;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@AllArgsConstructor
public class MatchingResult {
    private final List<ToneOnTone> toneOnTones;
    private final List<ToneInTone> toneInTones;

    public static MatchingResult of(final List<ToneOnTone> toneOnTones, final List<ToneInTone> toneIntones) {
        return new MatchingResult(toneOnTones, toneIntones);
    }
}
