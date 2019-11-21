package com.example.agenda.presenter;

import com.example.agenda.model.Contato;

import java.util.List;

public interface OnChangeContatoListener {

    void update(List<Contato> contatos);
}
