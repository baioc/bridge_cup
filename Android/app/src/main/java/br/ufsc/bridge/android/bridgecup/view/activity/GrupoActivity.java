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

    RecyclerView selecoesRecyclerView = null;
    RecyclerView partidasRecyclerView = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grupo);

        //utiliza a Intent que iniciou essa activity e extrai dela o grupo enviado
        //@todo aumentar a "clickBox" das selecoes e das partidas
        //@note recebe o id do grupo e nao a classe Grupo de forma serializada
        //pois assim eh possivel chamar essa tela de qualquer outro lugar passando apenas o id
        Intent intent = getIntent();
        String grupo = (String) intent.getSerializableExtra("GRUPO");

        //configura o recyclerView para as selecoes
        selecoesRecyclerView = (RecyclerView) findViewById(R.id.rv_selecoes);
        selecoesRecyclerView.setHasFixedSize(true);
        selecoesRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        //configura o recyclerView para as partidas
        partidasRecyclerView = (RecyclerView) findViewById(R.id.rv_partidas);
        partidasRecyclerView.setHasFixedSize(true);
        partidasRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        connectAndFillGrupo(grupo);
    }


    /** Preenche os RecyclerViews dessa classe utilizando a API REST */
    private void connectAndFillGrupo(final String idGrupo){
        WorldCupApi api = WorldCupApiUtil.getClient();//conecta a api
        Call<Grupo> grupoCall = api.getGrupo(idGrupo);//GET grupo

        //utiliza a resposta da api (processada em uma thread no background)
        grupoCall.enqueue( new Callback<Grupo>() {
            @Override
            public void onResponse(Call<Grupo> call, Response<Grupo> response) {
                Grupo grupo = response.body();

                //utiliza os adapters para preecher cada item seguindo os modelos de layout
                selecoesRecyclerView.setAdapter(new SelecoesAdapter(grupo.getSelecoes(), R.layout.item_selecao, getApplicationContext()));
                partidasRecyclerView.setAdapter(new PartidasAdapter(grupo, R.layout.item_partida, getApplicationContext()));
            }

            @Override
            public void onFailure(Call<Grupo>  call, Throwable throwable) {
                Toast.makeText( getApplicationContext(),
                            getString(R.string.load_error_at) + getApplicationContext().getClass().getSimpleName(),
                                Toast.LENGTH_SHORT).show();
            }
        });
    }

}
