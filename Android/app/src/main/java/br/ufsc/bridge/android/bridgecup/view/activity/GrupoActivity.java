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
    private Grupo grupo = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grupo);

        //configura os recyclerViews
        selecoesRecyclerView = (RecyclerView) findViewById(R.id.rv_selecoes);
        selecoesRecyclerView.setHasFixedSize(true);
        selecoesRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        partidasRecyclerView = (RecyclerView) findViewById(R.id.rv_partidas);
        partidasRecyclerView.setHasFixedSize(true);
        partidasRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        //utiliza a Intent que iniciou essa activity e extrai dela o grupo enviado
        //@todo aumentar a "clickBox" das selecoes e das partidas
        Intent intent = getIntent();
        grupo = (Grupo) intent.getSerializableExtra("GRUPO");
        String idGrupo = (String) intent.getSerializableExtra("ID_GRUPO");

        //dependendo de como a activity foi iniciada, decide se deve utilizar a api
        if (grupo == null) {
            connectAndFillSelecoesPartidas(idGrupo);
        }

        //utiliza os adapters para preecher cada item seguindo os modelos de layout
        selecoesRecyclerView.setAdapter(new SelecoesAdapter(grupo.getSelecoes(), R.layout.item_selecao, getApplicationContext()));
        partidasRecyclerView.setAdapter(new PartidasAdapter(grupo, R.layout.item_partida, getApplicationContext()));
    }


    /** Preenche os RecyclerViews dessa classe utilizando a API REST */
    private void connectAndFillSelecoesPartidas(String idGrupo){
        WorldCupApi api = WorldCupApiUtil.getClient();//conecta a api
        Call<Grupo> grupoCall = api.getGrupo(idGrupo);///GET grupo

        //utiliza a resposta da api (processada em uma thread no background)
        grupoCall.enqueue( new Callback<Grupo>() {
            @Override
            public void onResponse(Call<Grupo> call, Response<Grupo> response) {
                grupo = response.body();
            }

            @Override
            public void onFailure(Call<Grupo>  call, Throwable throwable) {
                Toast.makeText(getApplicationContext(), getString(R.string.load_error), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
