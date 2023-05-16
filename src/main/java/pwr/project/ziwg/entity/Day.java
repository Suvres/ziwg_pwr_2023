package pwr.project.ziwg.entity;

import com.google.cloud.Date;
import com.google.cloud.Timestamp;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pwr.project.ziwg.enums.Emotions;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Setter
@Getter
@ToString
public class Day {
    private String description = "";
    private String date = "";
    private Map<String, Emotions> emotions = new HashMap<>();

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !this.getClass().equals(obj.getClass())) {
            return false;
        }

        return this.date.equals(((Day) obj).date);
    }

    public String getDate() {
        return LocalDate.parse(date).format(DateTimeFormatter.ISO_DATE);
    }

    public void setDate(String date) {
        String kkk = LocalDate.parse(date).format(DateTimeFormatter.ISO_DATE);

        this.date = kkk;
    }

    public boolean equalDate(LocalDate dateToEqual) {
       return LocalDate.parse(date).equals(dateToEqual);
    }
}
