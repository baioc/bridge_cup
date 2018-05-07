package br.ufsc.bridge.android.bridgecup.model.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Edicao implements Serializable {

    // PRIVATE VARIABLES

    @SerializedName("ano")
    private Integer ano;

    @SerializedName("sede")
    private String sede;


    // CONSTRUCTORS

    public Edicao() {}

    /**
     * @param sede
     * @param ano
     */
    public Edicao(Integer ano, String sede) {
        this.ano = ano;
        this.sede = sede;
    }


    // GETTERS & SETTERS

    public Integer getAno() {
        return ano;
    }
    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public String getSede() {
        return sede;
    }
    public void setSede(String sede) {
        this.sede = sede;
    }

}