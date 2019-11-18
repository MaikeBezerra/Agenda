package com.example.agenda;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.agenda.data.ContatoDAO;
import com.example.agenda.data.ContatoDAOFirebase;
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
        contatoDAO = new ContatoDAOFirebase();

        etNome = (EditText) findViewById(R.id.txtNome);
        etTelefone = (EditText) findViewById(R.id.txtFone);
        etEndereco = (EditText) findViewById(R.id.txtEndereco);
    }

    public void adicionar(View view){
        Contato c = new Contato(etNome.getText().toString(), etTelefone.getText().toString(), etEndereco.getText().toString());
        contatoDAO.addContato(c);
        Toast.makeText(this, "Contato adicionado", Toast.LENGTH_LONG).show();
    }

    public void cancelar(View view){}
}
