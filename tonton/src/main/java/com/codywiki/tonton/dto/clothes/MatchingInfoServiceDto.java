package com.codywiki.tonton.dto.clothes;

import com.codywiki.tonton.controller.dto.clothes.MatchingInfoDto;
import com.codywiki.tonton.entity.enums.ClothesTag;
import com.codywiki.tonton.entity.enums.Color;
import com.codywiki.tonton.entity.enums.Saturation;
import com.codywiki.tonton.entity.enums.Value;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MatchingInfoServiceDto {
    private final ClothesTag majorTag;
    private final Color color;
    private final Saturation saturation;
    private final Value value;

    public static MatchingInfoServiceDto of(final MatchingInfoDto matchingResultDto) {
        ClothesTag majorTag = ClothesTag.valueOf(matchingResultDto.getMajorTag());
        Color color = Color.valueOf(matchingResultDto.getColor());
        Saturation saturation = Saturation.findSaturation(matchingResultDto.getSaturation());
        Value value = Value.findValue(matchingResultDto.getValue());

        return new MatchingInfoServiceDto(majorTag, color, saturation, value);
    }
}
