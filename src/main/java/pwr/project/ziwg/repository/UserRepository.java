package pwr.project.ziwg.repository;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import org.springframework.stereotype.Repository;
import pwr.project.ziwg.entity.User;

@Repository
public class UserRepository extends Firebase<User> {

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
}
