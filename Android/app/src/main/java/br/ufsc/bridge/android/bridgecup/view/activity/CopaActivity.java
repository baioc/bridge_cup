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
    private RecyclerView gruposRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_copa);
        setTitle(R.string.label_cup);

        //configura o recyclerView : layout com os grupos
        //@todo Back Navigation @ https://developer.android.com/training/implementing-navigation/ancestral
        gruposRecyclerView = findViewById(R.id.rv_grupos);
        gruposRecyclerView.setHasFixedSize(true);
        gruposRecyclerView.setLayoutManager(new GridLayoutManager(CopaActivity.this, 2));

        connectAndGetGrupos(ANO);
    }


    /**
     * Carrega a lista de Grupos utilizando a API REST
     */
    private void connectAndGetGrupos(int ano){
        WorldCupApi api = WorldCupApiUtil.getClient();//conecta a api
        Call<List<Grupo>> gruposCall = api.getGrupos(ano);//GET grupos

        //utiliza a resposta da api (processada em uma thread no background)
        gruposCall.enqueue( new Callback<List<Grupo>>() {
            @Override
            public void onResponse(Call<List<Grupo>> call, Response<List<Grupo>> response) {
                List<Grupo> grupos = response.body();
                fillGrupos(grupos);
            }

            @Override
            public void onFailure(Call<List<Grupo>>  call, Throwable throwable) {
                Toast.makeText(CopaActivity.this, getString(R.string.load_error), Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * Utiliza o adapter para preecher cada Grupo
     */
    private void fillGrupos(List<Grupo> grupos) {
        gruposRecyclerView.setAdapter(new GruposAdapter(grupos, R.layout.item_grupo, CopaActivity.this));
    }

}
