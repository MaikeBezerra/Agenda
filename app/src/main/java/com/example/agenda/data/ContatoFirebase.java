package com.example.agenda.data;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.agenda.model.Contato;
import com.example.agenda.presenter.OnChangeContatoListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.content.ContentValues.TAG;

public class ContatoFirebase implements ContatoDAO {

    private static final String COLECAO = "contatos";

    private FirebaseFirestore firestore;
    private Context context;

    private List<Contato> contatos;
    private List<OnChangeContatoListener> observers;
    private Contato isloged;

    public ContatoFirebase(Context context) {
        this.firestore = FirebaseFirestore.getInstance();
        this.context = context;

        this.contatos = new ArrayList<>();
        this.observers = new ArrayList<>();
    }

    @Override
    public void addContato(String nome, String telefone, String endereco) {
        Map<String, Object> contato = new HashMap<>();
        contato.put("nome", nome);
        contato.put("telefone", telefone);
        contato.put("endereco", endereco);

        firestore.collection("agendas")
                .document("97016706")
                .update("contatos", FieldValue.arrayUnion(contato));

    }

    public void logarContato(String telefone){
        firestore.collection("contatos")
                .whereEqualTo("telefone", telefone)
                .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                List<Contato> contatos = queryDocumentSnapshots.toObjects(Contato.class);
                setIsloged(contatos.get(0));
            }
        });
    }

    @Override
    public void editContato(Contato c) {
        //this.databaseContato.child(c.getId()).setValue(c);
    }

    @Override
    public void deleteContato(int contatoId) {

    }

    @Override
    public Contato getContato(int contatoId) {
        return null;
    }

    @Override
    public Contato getContatoByTelefone(String telefone){

        return null;
    }

    @Override
    public void findAll() {
        firestore.collection(COLECAO)
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
                        Log.w(TAG, "Error while reading list of contatos ", e);
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

    private Contato getIsloged(){
        return isloged;
    }

    public void setIsloged(Contato contato){
        this.isloged = contato;
    }

}
