package com.example.agenda.data;

import com.example.agenda.model.Contato;
import com.example.agenda.presenter.OnChangeContatoListener;

public interface ContatoDAO{
    void addContato( Contato c);
    void editContato( Contato c );
    void deleteContato( int contatoId );
    Contato getContato( int contatoId );

    void findAll();

    void addObserver(OnChangeContatoListener observer);
}
