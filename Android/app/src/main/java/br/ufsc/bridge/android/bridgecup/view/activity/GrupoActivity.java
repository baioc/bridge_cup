package br.ufsc.bridge.android.bridgecup.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import br.ufsc.bridge.android.bridgecup.R;
import br.ufsc.bridge.android.bridgecup.model.entity.Grupo;
import br.ufsc.bridge.android.bridgecup.view.adapter.PartidasAdapter;
import br.ufsc.bridge.android.bridgecup.view.adapter.SelecoesAdapter;

public class GrupoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grupo);

        //utiliza a Intent que iniciou essa activity e extrai dela o grupo enviado
        //@todo aumentar a "clickBox" das selecoes e das partidas
        Intent intent = getIntent();
        Grupo grupo = (Grupo) intent.getSerializableExtra("GRUPO");

        //configura o recyclerView para as selecoes
        RecyclerView selecoesRecyclerView = (RecyclerView) findViewById(R.id.rv_selecoes);
        selecoesRecyclerView.setHasFixedSize(true);
        selecoesRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        //configura o recyclerView para as partidas
        RecyclerView partidasRecyclerView = (RecyclerView) findViewById(R.id.rv_partidas);
        partidasRecyclerView.setHasFixedSize(true);
        partidasRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        //@todo talvez fazer uma funcao para configurar os recyclerViews
        //utiliza os adapters para preecher cada item seguindo os modelos de layout
        selecoesRecyclerView.setAdapter(new SelecoesAdapter(grupo.getSelecoes(), R.layout.item_selecao, getApplicationContext()));
        partidasRecyclerView.setAdapter(new PartidasAdapter(grupo, R.layout.item_partida, getApplicationContext()));
    }


}
