package br.ufsc.bridge.android.bridgecup.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import br.ufsc.bridge.android.bridgecup.R;
import br.ufsc.bridge.android.bridgecup.model.entity.Grupo;
import br.ufsc.bridge.android.bridgecup.model.entity.Selecao;
import br.ufsc.bridge.android.bridgecup.view.adapter.SelecoesAdapter;

public class GrupoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grupo);

        // utiliza a Intent que iniciou essa activity e extrai dela o grupo enviado
        Intent intent = getIntent();
        Grupo grupo = (Grupo) intent.getSerializableExtra("GRUPO");

        //configura o recyclerView para as selecoes
        RecyclerView selecoesRecyclerView = (RecyclerView) findViewById(R.id.rv_selecoes);
        selecoesRecyclerView.setHasFixedSize(true);
        selecoesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<Selecao> selecoes = grupo.getSelecoes();
        //utiliza o adapter para preecher cada item seguindo o modelo de layout
        selecoesRecyclerView.setAdapter(new SelecoesAdapter(selecoes, R.layout.item_selecao, getApplicationContext()));
    }

}
