package pwr.project.ziwg.dto;

import lombok.*;
import pwr.project.ziwg.enums.EmotionsCategories;
import pwr.project.ziwg.enums.FisInput;
import pwr.project.ziwg.services.FisService;

import java.nio.FloatBuffer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class DaysEmotionCategoryDto {
    private String date;
    private FisInput emotionsCategories;

    public static DaysEmotionCategoryDto of(DaysEmotionsCategoryDto day) {
        return DaysEmotionCategoryDto.builder()
                .date(day.getDate())
                .emotionsCategories(getCategorie(day.getEmotionsCategories().values().stream().toList()))
                .build();
    }

    private static FisInput getCategorie(List<EmotionsCategories> categoriesList) {
        Map<FisInput, Float> counter = new HashMap<>();
        counter.put(FisInput.SMUTEK, 0f);
        counter.put(FisInput.RADOSC, 0f);
        counter.put(FisInput.DEPRESJA, 0f);

        categoriesList.stream()
                .map(FisInput::getFromCategory)
                .filter(Objects::nonNull)
                .forEach(d -> counter.compute(d, (key, old) -> old + 1));


        float all = counter.values().stream().reduce(0f, Float::sum);
        float restSmutek = counter.get(FisInput.SMUTEK) / all;
        float restDepresja = counter.get(FisInput.DEPRESJA) / all;

        if(restSmutek >= 0.5)  {
            return FisInput.SMUTEK;
        }

        if(restDepresja >= 0.5) {
            return FisInput.DEPRESJA;
        }

        return FisInput.RADOSC;
    }

}