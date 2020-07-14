package com.example.model;

import com.orm.SugarRecord;

public class AmbientesModel extends SugarRecord {

    private String NomeAmbiente;
    private String Descricao;
    private Locais localAmbiente;


    public AmbientesModel(String nomeAmbiente, String descricao, Locais localAmbiente) {
        NomeAmbiente = nomeAmbiente;
        Descricao = descricao;
        this.localAmbiente = localAmbiente;
    }


    public Locais getLocalAmbiente() {
        return localAmbiente;
    }

    public void setLocalAmbiente(Locais localAmbiente) {
        this.localAmbiente = localAmbiente;
    }

    public AmbientesModel() {
    }

    public String getNomeAmbiente() {
        return NomeAmbiente;
    }

    public void setNomeAmbiente(String nomeAmbiente) {
        NomeAmbiente = nomeAmbiente;
    }

    public String getDescricao() {
        return Descricao;
    }

    public void setDescricao(String descricao) {
        Descricao = descricao;
    }
}
