package pwr.project.ziwg.repository;

import com.google.cloud.firestore.DocumentSnapshot;
import org.springframework.stereotype.Repository;
import pwr.project.ziwg.entity.User;

import java.util.concurrent.ExecutionException;

@Repository
public class UserRepository extends Firebase<User> {

    public UserRepository() {
        super("user");
    }
}
