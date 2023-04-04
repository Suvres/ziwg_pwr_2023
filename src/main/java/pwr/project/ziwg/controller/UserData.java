package pwr.project.ziwg.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pwr.project.ziwg.dto.PlacesDto;
import pwr.project.ziwg.entity.Day;
import pwr.project.ziwg.entity.User;
import pwr.project.ziwg.repository.UserRepository;

import java.time.LocalDate;

@RestController
@RequestMapping(value = "/user")
@RequiredArgsConstructor
public class UserData {

    private final UserRepository userRepository;

    @GetMapping(value = "/{uid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getDetails(@PathVariable String uid) {
        return ResponseEntity.ok(userRepository.getDocumentByUid(uid));
    }

    @PostMapping(value = "/{uid}/day", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> setDays(@PathVariable String uid, @RequestBody Day day) {
        userRepository.editOrAddDays(uid, day);
        return ResponseEntity.ok(String.format("uid: %s, day: %s", uid, day.toString()));
    }

    @PostMapping(value = "/{uid}/place", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> setPlaces(@PathVariable String uid, @RequestBody PlacesDto place) {
        userRepository.addOrEditPlace(uid, place.places);
        return ResponseEntity.ok(String.format("uid: %s, place: %s", uid, place));
    }

    @DeleteMapping("/{uid}/{date}")
    public ResponseEntity<String> deleteDay(@PathVariable String uid, @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        userRepository.deleteByDate(date, userRepository.getDocumentByUid(uid));
        return ResponseEntity.ok(String.format("uid: %s, date: %s", uid, date));
    }


    @DeleteMapping(value = "/{uid}/place", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deletePlaces(@PathVariable String uid, @RequestBody PlacesDto place) {
        userRepository.deletePlace(uid, place.places);
        return ResponseEntity.ok(String.format("uid: %s, place: %s", uid, place));
    }
}
