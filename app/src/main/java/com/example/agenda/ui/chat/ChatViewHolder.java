package com.example.agenda.ui.chat;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agenda.R;

public class ChatViewHolder extends RecyclerView.ViewHolder {

    private TextView showMesage;
    private ImageView imgContato;

    public ChatViewHolder(@NonNull View itemView) {
        super(itemView);

        showMesage = itemView.findViewById(R.id.show_mesage);
        imgContato = itemView.findViewById(R.id.imgContato);
    }

    public void setShowMesage(String showMesage){
        this.showMesage.setText(showMesage);
    }

    public void setImgContato(){}
}
