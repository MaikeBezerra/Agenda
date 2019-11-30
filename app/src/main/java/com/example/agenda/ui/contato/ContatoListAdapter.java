package com.example.agenda.ui.contato;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agenda.MensagensActivity;
import com.example.agenda.R;
import com.example.agenda.model.Contato;
import com.example.agenda.model.Mensagem;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;
import java.util.Map;

public class ContatoListAdapter extends RecyclerView.Adapter<ContataoListViewHolder> {

    private Context context;
    private List<Contato> contatos;


    ContatoListAdapter(Context context, List<Contato> contatos){
        this.context = context;
        this.contatos = contatos;
    }

    @NonNull
    @Override
    public ContataoListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.line_contato_list, parent, false);
        return new ContataoListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContataoListViewHolder holder, int position) {
        final Contato contato = contatos.get(position);
        holder.setNomeContato(contato.getNome());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MensagensActivity.class);
                intent.putExtra("telefone", contato.getTelefone());
                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return contatos.size();
    }
}
