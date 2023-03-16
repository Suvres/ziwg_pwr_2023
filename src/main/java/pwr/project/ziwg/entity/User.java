package pwr.project.ziwg.entity;

import com.google.firebase.auth.UserRecord;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
public class User extends FirestoreEntity {
    private String name;
    private String email;

    public static User createFromUserRecord(UserRecord userRecord) {
        User user = new User();
        user.uid = userRecord.getUid();
        user.name = userRecord.getDisplayName();
        user.email = userRecord.getEmail();

        return user;
    }
}
