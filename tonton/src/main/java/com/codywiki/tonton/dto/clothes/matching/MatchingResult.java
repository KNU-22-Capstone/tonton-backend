package com.codywiki.tonton.dto.clothes.matching;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@AllArgsConstructor
public class MatchingResult {
    private final List<ToneInTone> toneInTones;
    private final List<ToneAndTone> toneAndTones;

    public static MatchingResult of(final List<ToneInTone> toneInTones, final List<ToneAndTone> toneAndTones) {
        return new MatchingResult(toneInTones, toneAndTones);
    }
}
