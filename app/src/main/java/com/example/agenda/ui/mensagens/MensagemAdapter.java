package com.example.agenda.ui.mensagens;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agenda.MensagensActivity;
import com.example.agenda.R;
import com.example.agenda.model.Mensagem;


import java.util.List;

public class MensagemAdapter extends RecyclerView.Adapter<MensagemViewHolder> {
    Context context;
    private List<Mensagem> mensagens;

    MensagemAdapter(Context context, List<Mensagem> mensagens){
        this.context = context;
        this.mensagens = mensagens;
    }

    @NonNull
    @Override
    public MensagemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.line_message, parent, false);
        return new MensagemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MensagemViewHolder holder, int position) {
        final Mensagem mensagem = mensagens.get(position);
        holder.setNomeContato(mensagem.getNomeDestinatario());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MensagensActivity.class);
                intent.putExtra("telefone", mensagem.getReceiver());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
