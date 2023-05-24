package pwr.project.ziwg.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pwr.project.ziwg.dto.DaysEmotionsCategoryDto;
import pwr.project.ziwg.dto.PlacesDto;
import pwr.project.ziwg.entity.Day;
import pwr.project.ziwg.entity.User;
import pwr.project.ziwg.repository.UserRepository;
import pwr.project.ziwg.services.FisService;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/user")
@RequiredArgsConstructor
public class UserData {

    private final UserRepository userRepository;
    private final FisService fisService;

    @GetMapping(value = "/{uid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getDetails(@PathVariable String uid) {
        return ResponseEntity.ok(userRepository.getDocumentByUid(uid));
    }

    @GetMapping(value = "/{uid}/fis", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, String>> getEmotionFis(@PathVariable String uid)  {
        try {
            String result = Optional.ofNullable(fisService.getEmotionsFis(uid)).orElse("BRAK");
            return ResponseEntity.ok(Map.of("comments", result));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping(value = "/{uid}/emotions", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<DaysEmotionsCategoryDto>> getUserEmotions(@PathVariable String uid) {
        return ResponseEntity.ok(userRepository.getEmotionsByUserUid(uid));
    }

    @PostMapping(value = "/{uid}/day", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> setDays(@PathVariable String uid, @RequestBody Day day) {
        userRepository.editOrAddDays(uid, day);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/{uid}/place", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> setPlaces(@PathVariable String uid, @RequestBody PlacesDto place) {
        userRepository.addOrEditPlace(uid, place.places);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{uid}/{date}")
    public ResponseEntity<Void> deleteDay(@PathVariable String uid, @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        userRepository.deleteByDate(date, userRepository.getDocumentByUid(uid));
        return ResponseEntity.ok().build();
    }


    @DeleteMapping(value = "/{uid}/place", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deletePlaces(@PathVariable String uid, @RequestBody PlacesDto place) {
        userRepository.deletePlace(uid, place.places);
        return ResponseEntity.ok(String.format("uid: %s, place: %s", uid, place));
    }
}
