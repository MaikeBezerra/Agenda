package com.example.agenda.data;

import com.example.agenda.model.Agenda;
import com.example.agenda.presenter.OnContatoListEventListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class ContatoListFirebase {

    private static final String COLECAO = "agendas";
    private OnContatoListEventListener eventListener;

    private FirebaseFirestore firestore;

    public ContatoListFirebase(OnContatoListEventListener eventListener){
        this.eventListener = eventListener;
        this.firestore = FirebaseFirestore.getInstance();
    }

    public void find(String telefone){
        firestore.collection(COLECAO)
                .document(telefone)
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        Agenda agenda = documentSnapshot.toObject(Agenda.class);
                        if(agenda != null){
                            eventListener.onSetList(agenda.getContatos());
                        }

                    }
                });
    }

}
