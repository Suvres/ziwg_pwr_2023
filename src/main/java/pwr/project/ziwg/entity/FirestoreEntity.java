package pwr.project.ziwg.entity;

import lombok.Getter;

import java.util.UUID;

@Getter
public abstract class FirestoreEntity {
    protected String uid;

    public FirestoreEntity() {
        this.uid = UUID.randomUUID().toString();
    }
}
