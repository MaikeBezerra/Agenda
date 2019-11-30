package com.example.agenda.model;

import java.util.List;

public class Agenda {

    private List<Contato> contatos;

    public Agenda() {
    }

    public List<Contato> getContatos() {
        return contatos;
    }

    public void setContatos(List<Contato> contatos) {
        this.contatos = contatos;
    }

}
