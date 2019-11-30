package com.example.agenda.ui.mensagens;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agenda.R;

public class MensagemViewHolder extends RecyclerView.ViewHolder {
    private TextView nomeContato;
    private ImageView imgContato;
    private TextView corpoMensagem;

    public MensagemViewHolder(@NonNull View itemView) {
        super(itemView);
        nomeContato = itemView.findViewById(R.id.txtContatoName2);
        imgContato = itemView.findViewById(R.id.imgContato2);
        corpoMensagem = itemView.findViewById(R.id.corpoMensagem);
    }

    public void setNomeContato(String nome){
        nomeContato.setText(nome);
    }

    public void setCorpoMensagem(String corpo){
        corpoMensagem.setText(corpo);
    }
}
