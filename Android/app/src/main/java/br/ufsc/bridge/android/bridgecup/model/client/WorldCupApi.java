package br.ufsc.bridge.android.bridgecup.model.client;

import java.util.List;

import br.ufsc.bridge.android.bridgecup.model.entity.Edicao;
import br.ufsc.bridge.android.bridgecup.model.entity.Grupo;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/** Traz os metodos da API da copa:
 * getEdicoes();
 * getGrupos(ano);
 * ambos respondem com uma lista dinamica de tipo Edicao e Grupo, respectivamente.
 */
public interface WorldCupApi {

    @GET("edicoes")
    Call<List<Edicao>> getEdicoes();

    @GET("edicoes/{ano}/grupos")
    Call<List<Grupo>> getGrupos(@Path("ano") int ano);
}
