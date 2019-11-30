package com.example.agenda.model;

public class Mensagem {

    private String sender;
    private String receiver;
    private String corpo;
    private String nomeDestinatario;

    public Mensagem(String sender, String receiver, String corpo) {
        this.sender = sender;
        this.receiver = receiver;
        this.corpo = corpo;
    }

    public Mensagem() {
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getCorpo() {
        return corpo;
    }

    public void setCorpo(String corpo) {
        this.corpo = corpo;
    }

    public String getNomeDestinatario() {
        return this.nomeDestinatario;
    }
}
