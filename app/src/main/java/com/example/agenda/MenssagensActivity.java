package com.example.agenda;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.agenda.model.Chat;
import com.example.agenda.ui.chat.ChatAdapter;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MenssagensActivity extends AppCompatActivity {

    TextView userName;

    ImageButton btnSend;
    EditText txtSend;

    RecyclerView recyclerView;

    ChatAdapter chatAdapter;
    List<Chat> chats;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menssagens);
        
        userName = findViewById(R.id.username);
        btnSend = findViewById(R.id.btn_send);
        txtSend = findViewById(R.id.text_send);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayout = new LinearLayoutManager(getApplicationContext());
        linearLayout.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayout);

        String receiver = getIntent().getExtras().getString("telefone");
        readMesages(receiver);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = txtSend.getText().toString();
                String receiver = getIntent().getExtras().getString("telefone");

                if(!msg.isEmpty()) {
                    sendMesage("97016706", receiver, msg);
                } else {
                    Toast.makeText(getApplicationContext(), "You can't send empty message", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    private void sendMesage(String sender, String receiver, String mesage){


        Map<String, Object> mensagem = new HashMap<>();
        mensagem.put("sender", sender);
        mensagem.put("receiver", receiver);
        mensagem.put("message", mesage);

        FirebaseFirestore firestore = FirebaseFirestore.getInstance();

        firestore.collection("Chats").add(mensagem);
    }

    private void readMesages(final String telefone){
        chats = new ArrayList<>();

        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        firestore.collection("Chats")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        chats.clear();
                        for (Chat chat : queryDocumentSnapshots.toObjects(Chat.class)){
                            if (chat.getSender().equals("97016706") && chat.getReceiver().equals(telefone)
                                || chat.getSender().equals(telefone) && chat.getReceiver().equals("97016706")){
                                chats.add(chat);
                            }
                        }
                        chatAdapter = new ChatAdapter(getApplicationContext(), chats);
                        recyclerView.setAdapter(chatAdapter);
                    }
                });
    }
}
