package br.ufsc.bridge.android.bridgecup.model.client;

import java.util.List;

import br.ufsc.bridge.android.bridgecup.model.entity.Edicao;
import br.ufsc.bridge.android.bridgecup.model.entity.Grupo;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface WorldCupApi {

    @GET("edicoes")
    Call<List<Edicao>> getEdicoes();

    @GET("edicoes/{ano}/grupos")
    Call<List<Grupo>> getGrupos(@Path("ano") int ano);
}
