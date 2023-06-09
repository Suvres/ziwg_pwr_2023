package pwr.project.ziwg.entity;

import com.google.firebase.auth.UserRecord;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
public class User extends FirestoreEntity {
    private String name = "";
    private String email = "";

    private List<Day> days = new ArrayList<>();
    private List<String> places = new ArrayList<>();


    public static User createFromUserRecord(UserRecord userRecord) {
        User user = new User();
        user.uid = userRecord.getUid();
        user.name = userRecord.getDisplayName();
        user.email = userRecord.getEmail();

        return user;
    }
}
