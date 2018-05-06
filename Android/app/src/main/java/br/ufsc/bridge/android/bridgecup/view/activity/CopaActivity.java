package br.ufsc.bridge.android.bridgecup.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.List;

import br.ufsc.bridge.android.bridgecup.R;
import br.ufsc.bridge.android.bridgecup.model.client.WorldCupApi;
import br.ufsc.bridge.android.bridgecup.model.client.WorldCupApiUtil;
import br.ufsc.bridge.android.bridgecup.model.entity.Grupo;
import br.ufsc.bridge.android.bridgecup.view.adapter.GruposAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CopaActivity extends AppCompatActivity {

    private static final int ANO = 2018;
    private RecyclerView gruposRecyclerView = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_copa);

        //configura o recyclerView : layout com os grupos
        //@todo adicionar background e mudar o titulo superior em cada tela diferente
        gruposRecyclerView = (RecyclerView) findViewById(R.id.rv_grupos);
        gruposRecyclerView.setHasFixedSize(true);
        gruposRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        connectAndGetGrupos(ANO);
    }


    //@fixme alguns icones nao carregam
    private void connectAndGetGrupos(final int ano){
        WorldCupApi api = WorldCupApiUtil.getClient();//conecta a api

        Call<List<Grupo>> grupos = api.getGrupos(ano);//GET grupos

        //utiliza a resposta da api (processada em uma thread no background)
        grupos.enqueue( new Callback<List<Grupo>>() {
            @Override
            public void onResponse(Call<List<Grupo>> call, Response<List<Grupo>> response) {
                //seta o recyclerView utilizando o modelo definido e preenchido em GruposAdapter
                List<Grupo> grupos = response.body();
                gruposRecyclerView.setAdapter(new GruposAdapter((List<Grupo>) grupos, R.layout.item_grupo, getApplicationContext()));
            }

            @Override
            public void onFailure(Call<List<Grupo>>  call, Throwable throwable) {
                Toast.makeText(getApplicationContext(), "API Load Error at " + CopaActivity.class.getSimpleName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
