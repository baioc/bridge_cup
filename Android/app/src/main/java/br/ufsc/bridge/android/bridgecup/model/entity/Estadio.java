package br.ufsc.bridge.android.bridgecup.model.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Estadio implements Serializable {

    // PRIVATE VARIABLES

    @SerializedName("idEstadio")
    private Integer idEstadio;

    @SerializedName("nome")
    private String nome;

    @SerializedName("cidade")
    private String cidade;

    @SerializedName("capacidade")
    private Integer capacidade;

    @SerializedName("fotoURL")
    private String fotoURL;

    @SerializedName("latitude")
    private String latitude;

    @SerializedName("longitude")
    private String longitude;


    // CONSTRUCTORS

    public Estadio() {}

    /**
     * @param cidade
     * @param idEstadio
     * @param fotoURL
     * @param longitude
     * @param nome
     * @param latitude
     * @param capacidade
     */
    public Estadio(Integer idEstadio, String nome, String cidade, Integer capacidade, String fotoURL, String latitude, String longitude) {
        this.idEstadio = idEstadio;
        this.nome = nome;
        this.cidade = cidade;
        this.capacidade = capacidade;
        this.fotoURL = fotoURL;
        this.latitude = latitude;
        this.longitude = longitude;
    }


    // GETTERS & SETTERS

    public Integer getIdEstadio() {
        return idEstadio;
    }
    public void setIdEstadio(Integer idEstadio) {
        this.idEstadio = idEstadio;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCidade() {
        return cidade;
    }
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public Integer getCapacidade() {
        return capacidade;
    }
    public void setCapacidade(Integer capacidade) {
        this.capacidade = capacidade;
    }

    public String getFotoURL() {
        return fotoURL;
    }
    public void setFotoURL(String fotoURL) {
        this.fotoURL = fotoURL;
    }

    public String getLatitude() {
        return latitude;
    }
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

}