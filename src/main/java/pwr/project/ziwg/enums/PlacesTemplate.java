package pwr.project.ziwg.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PlacesTemplate {
    CITY("Miasto"),
    WORK("Praca"),
    SCHOOL("Szkoła"),
    HOME("Dom"),
    RELATIONSHIP("Związek");


    private final String name;
}
