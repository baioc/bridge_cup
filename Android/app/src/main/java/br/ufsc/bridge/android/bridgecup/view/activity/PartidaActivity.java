package br.ufsc.bridge.android.bridgecup.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import br.ufsc.bridge.android.bridgecup.R;
import br.ufsc.bridge.android.bridgecup.model.entity.Partida;

public class PartidaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partida);

        //utiliza a Intent que iniciou essa activity e extrai dela a Partida enviada
        Intent intent = getIntent();
        Partida partida = (Partida) intent.getSerializableExtra("PARTIDA");
        Toast.makeText(getApplicationContext(), partida.getIdPartida().toString(), Toast.LENGTH_SHORT).show();
    }
}
