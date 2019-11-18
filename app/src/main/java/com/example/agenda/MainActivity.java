package com.example.agenda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contato_list);
    }

    public void adicionarContato(View view){
        Intent intent = new Intent(getApplicationContext(), ContatoActivity.class);
        startActivity(intent);
    }
}
