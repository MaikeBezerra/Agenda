package com.example.agenda;

import androidx.annotation.NonNull;
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
import com.example.agenda.model.Mensagem;
import com.example.agenda.ui.chat.ChatAdapter;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MensagensActivity extends AppCompatActivity {

    TextView userName;

    ImageButton btnSend;
    EditText txtSend;

    RecyclerView recyclerView;

    ChatAdapter chatAdapter;
    List<Mensagem> mensagens;

    private FirebaseFirestore firestore;

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

        firestore = FirebaseFirestore.getInstance();

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

    private void sendMesage(final String sender, final String receiver, final String corpo){

        Mensagem mensagem = new Mensagem(sender , receiver,  corpo);

        final Chat chat = new Chat(Arrays.asList( mensagem ));

        firestore = FirebaseFirestore.getInstance();

        final DocumentReference reference = firestore.collection("chats").document("97016706_" + receiver);
        final DocumentReference refReverse = firestore.collection("chats").document(receiver + "_97016706" );

        reference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Map<String, Object> mensagem = new HashMap<>();
                mensagem.put("sender" , sender);
                mensagem.put("receiver" , receiver);
                mensagem.put("corpo" , corpo);

                reference.update("mensagens", FieldValue.arrayUnion(mensagem));
                refReverse.update("mensagens", FieldValue.arrayUnion(mensagem));
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                reference.set(chat);
                refReverse.set(chat);
            }
        });
        mensagens.add(mensagem);
        //chatAdapter.notifyDataSetChanged();
    }

    private void readMesages(final String telefone){
        mensagens = new ArrayList<>();

        firestore = FirebaseFirestore.getInstance();

        firestore.collection("chats")
                .document("97016706_" + telefone)
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        mensagens.clear();
                        Chat chat = documentSnapshot.toObject(Chat.class);
                        if(chat != null) {
                            mensagens.addAll(chat.getMensagens());
                            chatAdapter = new ChatAdapter(getApplicationContext(), mensagens);
                            recyclerView.setAdapter(chatAdapter);
                        }
                    }
                });
    }
}
