package com.example.agenda;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

public class CadastroActivity extends AppCompatActivity {

    PhoneAuthCredential auth;

    private static final String FONE_TESTE = "88997016706";
    private static final String SMS_CODE = "123456";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

    }
    
}
