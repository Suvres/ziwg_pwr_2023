package pwr.project.ziwg.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import pwr.project.ziwg.enums.Emotions;

import java.time.LocalDate;
import java.util.Map;

@Getter
@Setter
@ToString
public class Day {
    private String description;
    private LocalDate date;
    private Map<String, Emotions> emotions;
}
