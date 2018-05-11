package br.ufsc.bridge.android.bridgecup.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

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

        //cria referencias ao layout
        TextView infoPartida = findViewById(R.id.tv_info_partida);
        TextView votosCasa = findViewById(R.id.tv_votos_casa);
        TextView votosEmpate = findViewById(R.id.tv_votos_empate);
        TextView votosFora = findViewById(R.id.tv_votos_fora);
        ImageView fotoEstadio = findViewById(R.id.iv_estadio);
        TextView infoEstadio = findViewById(R.id.tv_info_estadio);

        //preenche o layout
        infoPartida.setText(String.format("Horário: %s", unixToDateTime(partida.getDataHora())));
        votosCasa.setText(String.format("%d\n" + "%s", partida.getVotosCasa(), partida.getSelecaoCasa()));
        votosEmpate.setText(String.format("%d\n" + "%s", partida.getVotosEmpate(), getString(R.string.label_empate)));
        votosFora.setText(String.format("%d\n" + "%s", partida.getVotosFora(), partida.getSelecaoFora()));
        //
    }

    private String unixToDateTime(long unix) {
        Date date = new java.util.Date(unix*1000L);
        SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm");
        dateFormat.setTimeZone(java.util.TimeZone.getDefault());
        return dateFormat.format(date);
    }
}
