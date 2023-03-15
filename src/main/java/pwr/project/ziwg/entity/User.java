package pwr.project.ziwg.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class User {
    private String uid;
    private String name;
    private String lastName;
    private String email;
}
