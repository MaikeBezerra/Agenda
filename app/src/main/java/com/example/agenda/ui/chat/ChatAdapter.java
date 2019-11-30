package com.example.agenda.ui.chat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agenda.R;
import com.example.agenda.mock.Telefones;
import com.example.agenda.model.Mensagem;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatViewHolder> {

    private static final int MSG_TYPE_LEFT = 0;
    private static final int MSG_TYPE_RIGHT = 1;
    private Context context;
    private List<Mensagem> mensagens;

    public ChatAdapter(Context context, List<Mensagem> mensages){
        this.context = context;
        this.mensagens = mensages;
    }

    @NonNull
    @Override
    public ChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (viewType == MSG_TYPE_LEFT) {
             view = LayoutInflater.from(context).inflate(R.layout.chat_item_left, parent, false);
        } else {
            view = LayoutInflater.from(context).inflate(R.layout.chat_item_right, parent, false);
        }

        return new ChatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatViewHolder holder, int position) {
        Mensagem mensagem = mensagens.get(position);

        holder.setShowMesage(mensagem.getCorpo());
    }

    @Override
    public int getItemCount() {
        return mensagens.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (mensagens.get(position).getSender().equals(Telefones.MY_NUMBER)){
            return MSG_TYPE_RIGHT;
        }

        return MSG_TYPE_LEFT;
    }
}
