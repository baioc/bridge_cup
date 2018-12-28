package br.ufsc.bridge.android.bridgecup.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;

import br.ufsc.bridge.android.bridgecup.R;
import br.ufsc.bridge.android.bridgecup.model.client.WorldCupApi;
import br.ufsc.bridge.android.bridgecup.model.client.WorldCupApiUtil;
import br.ufsc.bridge.android.bridgecup.model.entity.Estadio;
import br.ufsc.bridge.android.bridgecup.model.entity.Partida;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PartidaActivity extends AppCompatActivity {

    private TextView infoPartida;
    private TextView votosCasa;
    private TextView votosEmpate;
    private TextView votosFora;
    private ImageView fotoEstadio;
    private TextView infoEstadio;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partida);

        //cria referencias ao layout
        infoPartida = findViewById(R.id.tv_info_partida);
        votosCasa = findViewById(R.id.tv_votos_casa);
        votosEmpate = findViewById(R.id.tv_votos_empate);
        votosFora = findViewById(R.id.tv_votos_fora);
        fotoEstadio = findViewById(R.id.iv_estadio);
        infoEstadio = findViewById(R.id.tv_info_estadio);

        //utiliza a Intent que iniciou essa activity e extrai dela a Partida enviada
        Intent intent = getIntent();
        Partida partida = (Partida) intent.getSerializableExtra("PARTIDA");
        Integer idPartida = (Integer) intent.getSerializableExtra("ID_PARTIDA");

        //dependendo de como a activity foi iniciada, decide se deve utilizar a api
        if (partida == null) {
            connectAndGetPartida(idPartida.intValue());
        } else {
            fillPartida(partida);
        }

        setTitle(infoPartida.getText().toString().substring(0, infoPartida.getText().toString().indexOf("\n")));
    }


    /**
     * Carrega a Partida utilizando a API REST
     */
    private void connectAndGetPartida(int idPartida){
        WorldCupApi api = WorldCupApiUtil.getClient();//conecta a api
        Call<Partida> partidaCall = api.getPartida(idPartida);//GET Partida

        //utiliza a resposta da api (processada em uma thread no background)
        partidaCall.enqueue( new Callback<Partida>() {
            @Override
            public void onResponse(Call<Partida> call, Response<Partida> response) {
                Partida partida = response.body();
                fillPartida(partida);
            }

            @Override
            public void onFailure(Call<Partida>  call, Throwable throwable) {
                Toast.makeText(PartidaActivity.this, getString(R.string.load_error), Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * Carrega o Estadio utilizando a API REST
     */
    private void connectAndGetEstadio(int idEstadio){
        WorldCupApi api = WorldCupApiUtil.getClient();//conecta a api
        Call<Estadio> estadioCall = api.getEstadio(idEstadio);//GET Estadio

        //utiliza a resposta da api (processada em uma thread no background)
        estadioCall.enqueue( new Callback<Estadio>() {
            @Override
            public void onResponse(Call<Estadio> call, Response<Estadio> response) {
                Estadio estadio = response.body();
                fillEstadio(estadio);
            }

            @Override
            public void onFailure(Call<Estadio>  call, Throwable throwable) {
                Toast.makeText(PartidaActivity.this, getString(R.string.load_error), Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * Preenche os dados carregados no layout
     */
    private void fillPartida(Partida partida) {
        String siglaCasa = partida.getSelecaoCasa();
        String siglaFora = partida.getSelecaoFora();
        String dateTime = unixToDateTime(partida.getDataHora());
        int separateDate = dateTime.indexOf(" ", 0);
        int votosCasa = partida.getVotosCasa();
        int votosFora = partida.getVotosFora();
        int votosEmpate = partida.getVotosEmpate();
        int votosTotal = votosCasa + votosEmpate + votosFora;

        //@todo pensar em alguma maneira inteligente de carregar as bandeiras/icones das selecoes
        //@todo tambem implementar o click em cada uma das selecoes indo para sua respectiva tela
        infoPartida.setText(String.format(
            "%s    %s    %s\n\n" +
            "Data: %s\n\n" +
            "Horário: %s",
            siglaCasa,
            getString(R.string.versus),
            siglaFora,
            dateTime.substring(0, separateDate),
            dateTime.substring(separateDate + 1, dateTime.length())
        ));

        this.votosCasa.setText(String.format("%d%%\n" + "%s", (votosCasa*100) / votosTotal, siglaCasa));
        this.votosEmpate.setText(String.format("%d%%\n" + "%s", (votosEmpate*100) / votosTotal, getString(R.string.empate)));
        this.votosFora.setText(String.format("%d%%\n" + "%s", (votosFora*100) / votosTotal, siglaFora));

        connectAndGetEstadio(partida.getIdEstadio());
    }

    /**
     * Preenche os dados carregados no layout
     */
    private void fillEstadio(Estadio estadio) {
        Picasso.get()
                .load(estadio.getFotoURL())
                .placeholder(android.R.drawable.ic_menu_gallery)
                .error(android.R.drawable.stat_notify_error)
                .into(fotoEstadio);

        infoEstadio.setText(String.format(
                "Nome: %s\n\n" +
                "Cidade: %s\n\n" +
                "Capacidade: %d pessoas",
                estadio.getNome(),
                estadio.getCidade(),
                estadio.getCapacidade()
        ));
    }

    /**
     * Converte uma representacao de data e hora puramente numerica para algo mais legivel
     * @param unix long em formato unixDateTime para uma
     * @return String em formato "dd/MM/yyyy HHhmm"
     */
    public static String unixToDateTime(long unix) {
        Date date = new java.util.Date(unix*1000L);
        SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm");
        dateFormat.setTimeZone(java.util.TimeZone.getDefault());
        String formatted = dateFormat.format(date);
        return formatted.replace(':', 'h');
    }
}
