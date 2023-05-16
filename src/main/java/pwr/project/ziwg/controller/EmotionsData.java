package pwr.project.ziwg.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pwr.project.ziwg.dto.PlacesDto;
import pwr.project.ziwg.entity.Day;
import pwr.project.ziwg.entity.User;
import pwr.project.ziwg.enums.Emotions;
import pwr.project.ziwg.repository.UserRepository;

import java.time.LocalDate;

@RestController
@RequestMapping(value = "/emotions")
@RequiredArgsConstructor
public class EmotionsData {
    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Emotions[]> getDetails() {
        return ResponseEntity.ok(Emotions.values());
    }
}
