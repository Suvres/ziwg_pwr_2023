package pwr.project.ziwg.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 *
 * Każda emocja będzie miała swój wykres podzielony na 4
 * [0, 15], z czego pierwszy zbiór zacznie się od 1
 * będzie, mało emocji, średnio emocji, dużo emocji, bardzo dużo emocji aż do 15 dnia
 *
 * a następnie możemy sprawdzać w regułach, czy szczęścia było więcej niż innych emocji
 */
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
