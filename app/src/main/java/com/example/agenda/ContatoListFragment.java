package com.example.agenda;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agenda.model.Contato;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ContatoListFragment extends Fragment {

    private RecyclerView recyclerView;

    private ContatoListAdapter adapter;
    private List<Contato> contatos;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contato_list, container, false);

        recyclerView = view.findViewById(R.id.contatoList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        contatos = new ArrayList<>();
        readContatos();

        return view;
    }

    private void readContatos() {
        FirebaseFirestore firestore = FirebaseFirestore.getInstance();

        firestore.collection("contatos")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        contatos.clear();
                        contatos = queryDocumentSnapshots.toObjects(Contato.class);
                        adapter = new ContatoListAdapter(getContext(), contatos);
                        recyclerView.setAdapter(adapter);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getContext(), "Error while listing", Toast.LENGTH_SHORT).show();
                    }
                });
    }

}
