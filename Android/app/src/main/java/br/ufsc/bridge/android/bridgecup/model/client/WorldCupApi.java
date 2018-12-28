package br.ufsc.bridge.android.bridgecup.model.client;

import java.util.List;

import br.ufsc.bridge.android.bridgecup.model.entity.Edicao;
import br.ufsc.bridge.android.bridgecup.model.entity.Estadio;
import br.ufsc.bridge.android.bridgecup.model.entity.Grupo;
import br.ufsc.bridge.android.bridgecup.model.entity.Partida;
import br.ufsc.bridge.android.bridgecup.model.entity.Selecao;
import br.ufsc.bridge.android.bridgecup.model.entity.Voto;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Interface para a API REST
 */
public interface WorldCupApi {
    /* ------------- Edições ------------- */

    //Listas de edições da copa do mundo
    @GET("edicoes")
    Call<List<Edicao>> getEdicoes();


    /* ------------- Grupos ------------- */

    //Listas de grupos de uma copa do mundo
    @GET("edicoes/{ano}/grupos")
    Call<List<Grupo>> getGrupos(@Path("ano") int ano);

    //Obter um grupo
    @GET("grupos/{idGrupo}")
    Call<Grupo> getGrupo(@Path("idGrupo") String idGrupo);


    /* ------------- Selecoes ------------- */

    //Lista de seleções de um grupo
    @GET("grupos/{idGrupo}/selecoes")
    Call<List<Selecao>> getSelecoesNoGrupo(@Path("idGrupo") String idGrupo);

    //Lista de seleções de uma copa
    @GET("edicoes/{ano}/selecoes")
    Call<List<Selecao>> getSelecoesNaCopa(@Path("ano") int ano);

    //Obter uma seleção
    @GET("selecoes/{sigla}")
    Call<Selecao> getSelecao(@Path("sigla") String sigla);


    /* ------------- Partidas ------------- */

    //Lista de partidas de um grupo
    @GET("grupos/{idGrupo}/partidas")
    Call<List<Partida>> getPartidasNoGrupo(@Path("idGrupo") String idGrupo);

    //Lista de partidas de uma seleção
    @GET("selecoes/{sigla}/partidas")
    Call<List<Partida>> getPartidasDaSelecao(@Path("sigla") String sigla);

    //Lista de partidas de uma copa
    @GET("edicoes/{ano}/partidas")
    Call<List<Partida>> getPartidasNaCopa(@Path("ano") int ano);

    //Obter uma partida
    @GET("partidas/{idPartida}")
    Call<Partida> getPartida(@Path("idPartida") int idPartida);


    /* ------------- Estadios ------------- */

    //Listas de estádios de uma copa do mundo
    @GET("edicoes/{ano}/estadios")
    Call<List<Estadio>> getEstadios(@Path("ano") int ano);

    //Obter um estádio
    @GET("estadios/{idEstadio}")
    Call<Estadio> getEstadio(@Path("idEstadio") int idEstadio);


    /* ------------- Votacao ------------- */

    //Votar no resultado de uma partida
    @POST("partidas/{idPartida}/votos")
    Call<Voto> postVoto(@Path("idPartida") int idPartida, @Field("voto") String voto);
}