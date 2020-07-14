package com.example.model;

import com.orm.SugarRecord;

public class Locais extends SugarRecord {

    private String nome_local;
    private String endereco_local;
    private  String nome_proprietario;

    public Locais() {
    }

    public Locais(String nome_local, String endereco_local, String nome_proprietario) {
        this.nome_local = nome_local;
        this.endereco_local = endereco_local;
        this.nome_proprietario = nome_proprietario;
    }

    public String getNome_local() {
        return nome_local;
    }

    public void setNome_local(String nome_local) {
        this.nome_local = nome_local;
    }

    public String getEndereco_local() {
        return endereco_local;
    }

    public void setEndereco_local(String endereco_local) {
        this.endereco_local = endereco_local;
    }

    public String getNome_proprietario() {
        return nome_proprietario;
    }

    public void setNome_proprietario(String nome_proprietario) {
        this.nome_proprietario = nome_proprietario;
    }
}
