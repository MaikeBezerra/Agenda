package com.example.agenda.data;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.agenda.model.Contato;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class ContatoDAOFirebase implements ContatoDAO{
    private DatabaseReference databaseContato;
    private FirebaseFirestore firestore;
    private Context context;

    public ContatoDAOFirebase(Context context) {
        this.databaseContato = FirebaseDatabase.getInstance().getReference("usuarios");
        this.firestore = FirebaseFirestore.getInstance();
        this.context = context;
    }

    @Override
    public void addContato(Contato contato) {
        firestore.collection("contatos")
                .add(contato)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(context, "Created success", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(context, "Error while creating", Toast.LENGTH_SHORT).show();
                    }
                });

    }


    @Override
    public void editContato(Contato c) {
        this.databaseContato.child(c.getId()).setValue(c);
    }

    @Override
    public void deleteContato(int contatoId) {

    }

    @Override
    public Contato getContato(int contatoId) {
        return null;
    }

    @Override
    public ArrayList<Contato> getListaContato() {
        return null;
    }
}
