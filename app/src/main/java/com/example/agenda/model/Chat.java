package com.example.agenda.model;

import java.util.List;

public class Chat {

    private List<Mensagem> mensagens;

    public Chat(List<Mensagem> mensagens) {
        this.mensagens = mensagens;
    }

    public Chat(){}

    public List<Mensagem> getMensagens() {
        return mensagens;
    }

    public void setMensagens(List<Mensagem> mensagens) {
        this.mensagens = mensagens;
    }
}