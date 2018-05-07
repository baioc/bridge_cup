package br.ufsc.bridge.android.bridgecup.model.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Grupo implements Serializable {

    // PRIVATE VARIABLES

    @SerializedName("idGrupo")
    private String idGrupo;

    @SerializedName("letra")
    private String letra;

    @SerializedName("ano")
    private Integer ano;

    @SerializedName("selecoes")
    private List<Selecao> selecoes = null;

    @SerializedName("partidas")
    private List<Partida> partidas = null;


    // CONSTRUCTORS

    public Grupo() {}

    /**
     * @param partidas
     * @param letra
     * @param selecoes
     * @param idGrupo
     * @param ano
     */
    public Grupo(String idGrupo, String letra, Integer ano, List<Selecao> selecoes, List<Partida> partidas) {
        this.idGrupo = idGrupo;
        this.letra = letra;
        this.ano = ano;
        this.selecoes = selecoes;
        this.partidas = partidas;
    }


    // GETTERS & SETTERS

    public String getIdGrupo() {
        return idGrupo;
    }
    public void setIdGrupo(String idGrupo) {
        this.idGrupo = idGrupo;
    }

    public String getLetra() {
        return letra;
    }
    public void setLetra(String letra) {
        this.letra = letra;
    }

    public Integer getAno() {
        return ano;
    }
    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public List<Selecao> getSelecoes() {
        return selecoes;
    }
    public void setSelecoes(List<Selecao> selecoes) {
        this.selecoes = selecoes;
    }

    public List<Partida> getPartidas() {
        return partidas;
    }
    public void setPartidas(List<Partida> partidas) {
        this.partidas = partidas;
    }

}