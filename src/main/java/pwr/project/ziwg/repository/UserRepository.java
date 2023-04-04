package pwr.project.ziwg.repository;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import pwr.project.ziwg.entity.User;

import java.time.LocalDate;

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
        this.firestore.collection(this.document)
                .document(user.getUid())
                .collection(this.DAYS)
                .document(date.toString())
                .delete();

        log.info(String.format("Próba usunięcia daty dla %s %s", user.toString(), date.toString()));
    }
}
