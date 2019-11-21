package com.example.agenda.data;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.agenda.model.Contato;
import com.example.agenda.presenter.OnChangeContatoListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ContatoDAOFirebase implements ContatoDAO {

    private DatabaseReference databaseContato;
    private FirebaseFirestore firestore;
    private Context context;

    private List<Contato> contatos;
    private List<OnChangeContatoListener> observers;

    public ContatoDAOFirebase(Context context) {
        this.databaseContato = FirebaseDatabase.getInstance().getReference("usuarios");
        this.firestore = FirebaseFirestore.getInstance();
        this.context = context;

        this.contatos = new ArrayList<>();
        this.observers = new ArrayList<>();
    }

    @Override
    public void addContato(Contato contato) {
        firestore.collection("contatos")
                .add(contato)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(context, "Contato criado!", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(context, "Erro ao salvar contato!", Toast.LENGTH_SHORT).show();
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
    public void findAll() {
        firestore.collection("contatos")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        contatos.clear();
                        contatos = queryDocumentSnapshots.toObjects(Contato.class);
                        onChangeContatoList();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(context, "Erro na listagem!", Toast.LENGTH_SHORT).show();
                    }
                });

    }

    @Override
    public void addObserver(OnChangeContatoListener observer){
        observers.add(observer);
    }

    private void onChangeContatoList(){
        for (OnChangeContatoListener observer: observers) {
            observer.update(contatos);
        }
    }

}
