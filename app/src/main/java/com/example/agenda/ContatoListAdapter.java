package com.example.agenda;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agenda.model.Contato;

import java.util.List;

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
        Contato contato = contatos.get(position);
        holder.setNomeContato(contato.getNome());
    }


    @Override
    public int getItemCount() {
        return contatos.size();
    }
}
