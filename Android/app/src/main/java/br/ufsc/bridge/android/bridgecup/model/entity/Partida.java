package br.ufsc.bridge.android.bridgecup.model.entity;

import com.google.gson.annotations.SerializedName;


public class Partida {

    // PRIVATE VARIABLES

    @SerializedName("idPartida")
    private Integer idPartida;

    @SerializedName("dataHora")
    private Integer dataHora;

    @SerializedName("selecaoCasa")
    private String selecaoCasa;

    @SerializedName("selecaoFora")
    private String selecaoFora;

    @SerializedName("idEstadio")
    private Integer idEstadio;

    @SerializedName("votosCasa")
    private Integer votosCasa;

    @SerializedName("votosFora")
    private Integer votosFora;

    @SerializedName("votosEmpate")
    private Integer votosEmpate;


    // CONSTRUCTORS

    public Partida() {}

    /**
     * @param votosCasa
     * @param votosEmpate
     * @param idPartida
     * @param idEstadio
     * @param votosFora
     * @param selecaoFora
     * @param dataHora
     * @param selecaoCasa
     */
    public Partida(Integer idPartida, Integer dataHora, String selecaoCasa, String selecaoFora, Integer idEstadio, Integer votosCasa, Integer votosFora, Integer votosEmpate) {
        this.idPartida = idPartida;
        this.dataHora = dataHora;
        this.selecaoCasa = selecaoCasa;
        this.selecaoFora = selecaoFora;
        this.idEstadio = idEstadio;
        this.votosCasa = votosCasa;
        this.votosFora = votosFora;
        this.votosEmpate = votosEmpate;
    }


    // GETTERS & SETTERS

    public Integer getIdPartida() {
        return idPartida;
    }
    public void setIdPartida(Integer idPartida) {
        this.idPartida = idPartida;
    }

    public Integer getDataHora() {
        return dataHora;
    }
    public void setDataHora(Integer dataHora) {
        this.dataHora = dataHora;
    }

    public String getSelecaoCasa() {
        return selecaoCasa;
    }
    public void setSelecaoCasa(String selecaoCasa) {
        this.selecaoCasa = selecaoCasa;
    }

    public String getSelecaoFora() {
        return selecaoFora;
    }
    public void setSelecaoFora(String selecaoFora) {
        this.selecaoFora = selecaoFora;
    }

    public Integer getIdEstadio() {
        return idEstadio;
    }
    public void setIdEstadio(Integer idEstadio) {
        this.idEstadio = idEstadio;
    }

    public Integer getVotosCasa() {
        return votosCasa;
    }
    public void setVotosCasa(Integer votosCasa) {
        this.votosCasa = votosCasa;
    }

    public Integer getVotosFora() {
        return votosFora;
    }
    public void setVotosFora(Integer votosFora) {
        this.votosFora = votosFora;
    }

    public Integer getVotosEmpate() {
        return votosEmpate;
    }
    public void setVotosEmpate(Integer votosEmpate) {
        this.votosEmpate = votosEmpate;
    }

}