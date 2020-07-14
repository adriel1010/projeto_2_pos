package com.example.model;

import com.example.vistoria.Ambientes;
import com.orm.SugarRecord;

public class ItensAmbienteModel extends SugarRecord {

    private String NomeItem;
    private String Descricao;
    private AmbientesModel ambiente;
    private String imagem1;
    private String imagem2;
    private String imagem3;


    public ItensAmbienteModel(String nomeItem, String descricao, AmbientesModel ambiente, String imagem1, String imagem2, String imagem3) {
        NomeItem = nomeItem;
        Descricao = descricao;
        this.ambiente = ambiente;
        this.imagem1 = imagem1;
        this.imagem2 = imagem2;
        this.imagem3 = imagem3;
    }

    public ItensAmbienteModel() {

    }

    public String getNomeItem() {
        return NomeItem;
    }

    public void setNomeItem(String nomeItem) {
        NomeItem = nomeItem;
    }

    public String getDescricao() {
        return Descricao;
    }

    public void setDescricao(String descricao) {
        Descricao = descricao;
    }

    public AmbientesModel getAmbiente() {
        return ambiente;
    }

    public void setAmbiente(AmbientesModel ambiente) {
        this.ambiente = ambiente;
    }

    public String getImagem1() {
        return imagem1;
    }

    public void setImagem1(String imagem1) {
        this.imagem1 = imagem1;
    }

    public String getImagem2() {
        return imagem2;
    }

    public void setImagem2(String imagem2) {
        this.imagem2 = imagem2;
    }

    public String getImagem3() {
        return imagem3;
    }

    public void setImagem3(String imagem3) {
        this.imagem3 = imagem3;
    }
}
