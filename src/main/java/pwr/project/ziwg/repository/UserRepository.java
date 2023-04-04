package pwr.project.ziwg.repository;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import pwr.project.ziwg.entity.Day;
import pwr.project.ziwg.entity.User;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
@Slf4j
public class UserRepository extends Firebase<User> {

    private final String DAYS = "days";

    public UserRepository() {
        super(User.class);
    }

    public void createIfNotExist(String uid) {
        User user = this.getDocumentByUid(uid);
        if(user != null) {
            return;
        }

        try {
            UserRecord userRecord = FirebaseAuth.getInstance().getUser(uid);
            this.saveDocument(User.createFromUserRecord(userRecord));
        } catch (FirebaseAuthException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteByDate(LocalDate date, User user){

        user.getDays()
                .stream().filter(day1 -> day1.getDate().equals(date))
                .findFirst().ifPresent(user.getDays()::remove);

        this.firestore.collection(this.document).document(user.getUid()).set(user);

        log.info(String.format("Próba usunięcia daty dla %s %s", user.toString(), date.toString()));
    }

    //TODO usuwanie miejsc zamiast add dodać remove w nowej funkcji
    public void addOrEditPlace(String uid, String places){
        User user = this.getDocumentByUid(uid);
        user.getPlaces().add(places);

        this.firestore.collection(this.document)
                .document(user.getUid())
                .set(user);
    }
    public void deletePlace(String uid, String places){
        User user = this.getDocumentByUid(uid);
        user.getPlaces().remove(places);

        this.firestore.collection(this.document)
                .document(user.getUid())
                .set(user);
    }

    public void editOrAddDays(String uid, Day day) {
        User user = this.getDocumentByUid(uid);

        user.getDays()
                .stream().filter(day1 -> day1.equals(day))
                .findFirst().ifPresent(user.getDays()::remove);

        user.getDays().add(day);

        this.firestore.collection(this.document).document(user.getUid()).set(user);
    }
}
