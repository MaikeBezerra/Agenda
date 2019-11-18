package com.example.agenda.data;

import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.agenda.model.Contato;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;

import java.io.FileInputStream;
import java.util.ArrayList;

public class ContatoDAOFirebase implements ContatoDAO{
    private DatabaseReference databaseContato;

//
//    FirebaseOptions options = new FirebaseOptions.Builder()
//            .setCredentials(GoogleCredentials.getApplicationDefault())
//            .setDatabaseUrl("https://<DATABASE_NAME>.firebaseio.com/")
//            .build();
//
//FirebaseApp.initializeApp(options);


    public ContatoDAOFirebase() {
        this.databaseContato = FirebaseDatabase.getInstance().getReference("usuarios");

    }

    @Override
    public void addContato(Contato c) {
        String id = databaseContato.push().getKey();
        c.setId(id);
        databaseContato.child(id).setValue(c);
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
