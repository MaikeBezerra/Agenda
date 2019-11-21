package com.example.agenda;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ContataoListViewHolder extends RecyclerView.ViewHolder {

    private TextView nomeContato;
    private ImageView imgContato;

    public ContataoListViewHolder(@NonNull View itemView) {
        super(itemView);

        nomeContato = itemView.findViewById(R.id.txtContatoName);
        imgContato = itemView.findViewById(R.id.imgContato);
    }

    public void setNomeContato(String nome){
        nomeContato.setText(nome);
    }

    public void setImgContato(){}
}
