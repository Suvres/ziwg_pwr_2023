package pwr.project.ziwg.repository;

import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import lombok.Getter;

import java.util.concurrent.ExecutionException;

public abstract class Firebase<DOCUMENT> {

    private Class<DOCUMENT> documentClass;

    @Getter
    public final String document;
    protected Firestore firestore = FirestoreClient.getFirestore();
    public Firebase(String document){
       this.document = document;
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
}