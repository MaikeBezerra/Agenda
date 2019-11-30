package com.example.agenda.data;

import com.example.agenda.model.Contato;
import com.example.agenda.presenter.OnChangeContatoListener;

public interface ContatoDAO{
    void addContato( String nome, String telefone, String endereco );
    void editContato( Contato c );
    void deleteContato( int contatoId );
    Contato getContato( int contatoId );

    Contato getContatoByTelefone(String telefone);

    void findAll();

    void addObserver(OnChangeContatoListener observer);
}
