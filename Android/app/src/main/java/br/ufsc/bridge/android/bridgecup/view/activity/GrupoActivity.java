package br.ufsc.bridge.android.bridgecup.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import br.ufsc.bridge.android.bridgecup.R;
import br.ufsc.bridge.android.bridgecup.model.client.WorldCupApi;
import br.ufsc.bridge.android.bridgecup.model.client.WorldCupApiUtil;
import br.ufsc.bridge.android.bridgecup.model.entity.Grupo;
import br.ufsc.bridge.android.bridgecup.view.adapter.PartidasAdapter;
import br.ufsc.bridge.android.bridgecup.view.adapter.SelecoesAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GrupoActivity extends AppCompatActivity {

    private RecyclerView selecoesRecyclerView;
    private RecyclerView partidasRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grupo);

        //configura os recyclerViews
        selecoesRecyclerView = findViewById(R.id.rv_selecoes);
        selecoesRecyclerView.setHasFixedSize(true);
        selecoesRecyclerView.setLayoutManager(new GridLayoutManager(GrupoActivity.this, 2));
        partidasRecyclerView = findViewById(R.id.rv_partidas);
        partidasRecyclerView.setHasFixedSize(true);
        partidasRecyclerView.setLayoutManager(new LinearLayoutManager(GrupoActivity.this));

        //utiliza a Intent que iniciou essa activity e extrai dela o grupo enviado
        Intent intent = getIntent();
        Grupo grupo = (Grupo) intent.getSerializableExtra("GRUPO");
        String idGrupo = intent.getStringExtra("ID_GRUPO");

        //dependendo de como a activity foi iniciada, decide se deve utilizar a api
        if (grupo == null) {
            setTitle("Grupo " + idGrupo.substring(0, 2));
            connectAndGetSelecoesPartidas(idGrupo);
        } else {
            setTitle("Grupo " + grupo.getLetra());
            fillSelecoesPartidas(grupo);
        }
    }


    /**
     * Carrega o Grupo utilizando a API REST
     */
    private void connectAndGetSelecoesPartidas(String idGrupo){
        WorldCupApi api = WorldCupApiUtil.getClient();//conecta a api
        Call<Grupo> grupoCall = api.getGrupo(idGrupo);//GET grupo

        //utiliza a resposta da api (processada em uma thread no background)
        grupoCall.enqueue( new Callback<Grupo>() {
            @Override
            public void onResponse(Call<Grupo> call, Response<Grupo> response) {
                Grupo grupo = response.body();
                fillSelecoesPartidas(grupo);
            }

            @Override
            public void onFailure(Call<Grupo>  call, Throwable throwable) {
                Toast.makeText(GrupoActivity.this, getString(R.string.load_error), Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * Utiliza o adapter para preecher os dados das Selecoes e Partidas no grupo
     */
    private void fillSelecoesPartidas(Grupo grupo) {
        selecoesRecyclerView.setAdapter(new SelecoesAdapter(grupo.getSelecoes(), R.layout.item_selecao, GrupoActivity.this));
        partidasRecyclerView.setAdapter(new PartidasAdapter(grupo, R.layout.item_partida, GrupoActivity.this));
    }
}
