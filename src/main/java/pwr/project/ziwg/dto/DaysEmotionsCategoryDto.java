package pwr.project.ziwg.dto;

import lombok.Builder;
import lombok.Getter;
import pwr.project.ziwg.enums.EmotionsCategories;

import java.util.HashMap;
import java.util.Map;

@Builder
@Getter
public class DaysEmotionsCategoryDto {
    private String date;
    private Map<String, EmotionsCategories> emotionsCategories = new HashMap<>();
}
