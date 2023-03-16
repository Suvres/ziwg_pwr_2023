package pwr.project.ziwg.repository;

import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import pwr.project.ziwg.entity.FirestoreEntity;

import java.util.concurrent.ExecutionException;

@Slf4j
public abstract class Firebase<DOCUMENT extends FirestoreEntity> {

    private final Class<DOCUMENT> documentClass;

    @Getter
    public final String document;
    protected Firestore firestore = FirestoreClient.getFirestore();
    public Firebase(Class<DOCUMENT> document){
        this.documentClass = document;
        this.document = document.getSimpleName();
    }

    public DOCUMENT getDocumentByUid(String uid) {
        try {
            DocumentSnapshot document = firestore.collection(this.document).document(uid).get().get();
            if(document.exists()) {
                return document.toObject(documentClass);
            } else {
                return null;
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveDocument(DOCUMENT object) {
        firestore.collection(this.document).document(object.getUid()).set(object);
        log.info(String.format("Zapisano obiekt: %s", object.toString()));
    }
}