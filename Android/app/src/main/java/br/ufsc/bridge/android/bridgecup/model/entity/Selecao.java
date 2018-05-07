package br.ufsc.bridge.android.bridgecup.model.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Selecao implements Serializable {

    // PRIVATE VARIABLES

    @SerializedName("sigla")
    private String sigla;

    @SerializedName("nome")
    private String nome;

    @SerializedName("vezesCampeao")
    private Integer vezesCampeao;

    @SerializedName("anoEstreia")
    private Integer anoEstreia;

    @SerializedName("totalParticipacoes")
    private Integer totalParticipacoes;

    @SerializedName("melhorResultado")
    private String melhorResultado;

    @SerializedName("iconeURL")
    private String iconeURL;

    @SerializedName("bandeiraURL")
    private String bandeiraURL;


    // CONSTRUCTORS

    public Selecao() {}

    /**
     * @param melhorResultado
     * @param sigla
     * @param bandeiraURL
     * @param iconeURL
     * @param vezesCampeao
     * @param totalParticipacoes
     * @param nome
     * @param anoEstreia
     */
    public Selecao(String sigla, String nome, Integer vezesCampeao, Integer anoEstreia, Integer totalParticipacoes, String melhorResultado, String iconeURL, String bandeiraURL) {
        this.sigla = sigla;
        this.nome = nome;
        this.vezesCampeao = vezesCampeao;
        this.anoEstreia = anoEstreia;
        this.totalParticipacoes = totalParticipacoes;
        this.melhorResultado = melhorResultado;
        this.iconeURL = iconeURL;
        this.bandeiraURL = bandeiraURL;
    }


    // GETTERS & SETTERS

    public String getSigla() {
        return sigla;
    }
    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getVezesCampeao() {
        return vezesCampeao;
    }
    public void setVezesCampeao(Integer vezesCampeao) {
        this.vezesCampeao = vezesCampeao;
    }

    public Integer getAnoEstreia() {
        return anoEstreia;
    }
    public void setAnoEstreia(Integer anoEstreia) {
        this.anoEstreia = anoEstreia;
    }

    public Integer getTotalParticipacoes() {
        return totalParticipacoes;
    }
    public void setTotalParticipacoes(Integer totalParticipacoes) {
        this.totalParticipacoes = totalParticipacoes;
    }

    public String getMelhorResultado() {
        return melhorResultado;
    }
    public void setMelhorResultado(String melhorResultado) {
        this.melhorResultado = melhorResultado;
    }

    public String getIconeURL() {
        return iconeURL;
    }
    public void setIconeURL(String iconeURL) {
        this.iconeURL = iconeURL;
    }

    public String getBandeiraURL() {
        return bandeiraURL;
    }
    public void setBandeiraURL(String bandeiraURL) {
        this.bandeiraURL = bandeiraURL;
    }

}