package pwr.project.ziwg.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EmotionsCategories {
    BAD("Słabość"),
    HAPPY("Radość"),
    ANGRY("Złość"),
    DISGUSTED("Zdegustowanie"),
    SUPRISED("Zaskoczenie"),
    FEARFULL("Obawy"),
    SAD("Smutek");

    private final String name;
}
