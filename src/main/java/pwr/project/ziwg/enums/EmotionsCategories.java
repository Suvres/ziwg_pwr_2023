package pwr.project.ziwg.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 *
 * Każda emocja będzie miała swój wykres podzielony na 4
 * [0, 15], z czego pierwszy zbiór zacznie się od 1
 * zbiory podzielimy na słabe emocje, nasilacjące się, duże emocje
 * zbiory dla fis
 *  małe: 1 -> 9 trapez
 *  nasilające się: 5 -
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
