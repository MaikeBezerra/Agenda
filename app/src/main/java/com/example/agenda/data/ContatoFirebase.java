package com.example.agenda.data;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.agenda.mock.Telefones;

import com.example.agenda.model.Agenda;
import com.example.agenda.model.Contato;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Arrays;
import java.util.HashMap;

import java.util.Map;

import static android.content.ContentValues.TAG;


public class ContatoFirebase implements ContatoDAO {

    private static final String COLECAO = "contatos";

    private FirebaseFirestore firestore;

    public ContatoFirebase(Context context) {
        this.firestore = FirebaseFirestore.getInstance();
    }

    @Override
    public void addContato(final String nome, final String telefone, final String endereco) {

        final DocumentReference reference = firestore.collection("agendas").document(Telefones.MY_NUMBER);
        final Agenda agenda = new Agenda(Arrays.asList(new Contato(nome, telefone, endereco)));

        reference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();

                    if (document.exists()) {
                        Map<String, Object> contato = new HashMap<>();
                        contato.put("nome", nome);
                        contato.put("telefone", telefone);
                        contato.put("endereco", endereco);

                        firestore.collection("agendas").
                                document(Telefones.MY_NUMBER)
                                .update(contato);
                    } else {
                        reference.set(agenda);
                    }
                } else {
                    reference.set(agenda);
                }
            }
        });
    }


}