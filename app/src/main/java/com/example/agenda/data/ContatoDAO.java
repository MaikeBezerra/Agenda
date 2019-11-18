package com.example.agenda.data;

import com.example.agenda.model.Contato;

import java.util.ArrayList;

public interface ContatoDAO {
    public void addContato( Contato c);
    public void editContato( Contato c );
    public void deleteContato( int contatoId );
    public Contato getContato( int contatoId );

    public ArrayList<Contato> getListaContato();
}
