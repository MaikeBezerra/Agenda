package com.example.agenda;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.agenda.data.ContatoDAO;
import com.example.agenda.data.ContatoFirebase;
import com.example.agenda.model.Contato;

public class ContatoActivity extends AppCompatActivity {
    ContatoDAO contatoDAO;
    private EditText etNome;
    private EditText etTelefone;
    private EditText etEndereco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contato);
        contatoDAO = new ContatoFirebase(getApplicationContext());

        etNome = findViewById(R.id.txtNome);
        etTelefone = findViewById(R.id.txtFone);
        etEndereco = findViewById(R.id.txtEndereco);
    }

    public void adicionar(View view){
        contatoDAO.addContato(etNome.getText().toString(), etTelefone.getText().toString(), etEndereco.getText().toString());
    }

    public void cancelar(View view){
        finish();
    }
}
