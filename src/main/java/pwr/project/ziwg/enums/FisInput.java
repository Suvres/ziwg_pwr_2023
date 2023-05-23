package pwr.project.ziwg.enums;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import pwr.project.ziwg.services.FisService;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Getter
public enum FisInput {
    RADOSC("RADOSC", List.of(EmotionsCategories.HAPPY, EmotionsCategories.SUPRISED)),
    SMUTEK("STRES", List.of(EmotionsCategories.BAD, EmotionsCategories.ANGRY, EmotionsCategories.FEARFULL)),
    DEPRESJA("DEPRESJA", List.of(EmotionsCategories.SAD, EmotionsCategories.DISGUSTED));


    private final String name;
    private final List<EmotionsCategories> categories;

    public static FisInput getFromCategory(EmotionsCategories categories) {
        return Arrays.stream(FisInput.values())
                .filter(i -> i.categories.contains(categories))
                .findFirst().orElse(null);
    }
}