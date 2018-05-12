package br.ufsc.bridge.android.bridgecup.model.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Voto implements Serializable {

    @SerializedName("voto")
    private String voto;

    /**
     * No args constructor for use in serialization
     *
     */
    public Voto() {}

    /**
     *
     * @param voto
     */
    public Voto(String voto) {
        super();
        this.voto = voto;
    }

    public String getVoto() {
        return voto;
    }

    public void setVoto(String voto) {
        this.voto = voto;
    }

}