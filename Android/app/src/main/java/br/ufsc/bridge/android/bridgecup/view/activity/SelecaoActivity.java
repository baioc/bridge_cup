package br.ufsc.bridge.android.bridgecup.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import br.ufsc.bridge.android.bridgecup.R;
import br.ufsc.bridge.android.bridgecup.model.client.WorldCupApi;
import br.ufsc.bridge.android.bridgecup.model.client.WorldCupApiUtil;
import br.ufsc.bridge.android.bridgecup.model.entity.Selecao;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SelecaoActivity extends AppCompatActivity {

    private ImageView bandeira;
    private TextView info;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selecao);

        //cria referencias ao layout
        bandeira = findViewById(R.id.iv_bandeira);
        info = findViewById(R.id.tv_info_selecao);

        //utiliza a Intent que iniciou essa activity e extrai dela a Selecao enviada
        Intent intent = getIntent();
        Selecao selecao = (Selecao) intent.getSerializableExtra("SELECAO");
        String sigla = intent.getStringExtra("SIG_SELECAO");

        //dependendo de como a activity foi iniciada, decide se deve utilizar a api
        if (selecao == null) {
            setTitle(sigla);
            connectAndGetSelecao(sigla);
        } else {
            setTitle(selecao.getNome());
            fillSelecao(selecao);
        }
    }


    /**
     * Carrega a Selecao utilizando a API REST
     */
    private void connectAndGetSelecao(String sigla){
        WorldCupApi api = WorldCupApiUtil.getClient();//conecta a api
        Call<Selecao> selecaoCall = api.getSelecao(sigla);//GET Selecao

        //utiliza a resposta da api (processada em uma thread no background)
        selecaoCall.enqueue( new Callback<Selecao>() {
            @Override
            public void onResponse(Call<Selecao> call, Response<Selecao> response) {
                Selecao selecao = response.body();
                fillSelecao(selecao);
            }

            @Override
            public void onFailure(Call<Selecao>  call, Throwable throwable) {
                Toast.makeText(SelecaoActivity.this, getString(R.string.load_error), Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * Preenche os dados carregados no layout
     */
    private void fillSelecao(Selecao selecao) {
        Picasso.get()
                .load(selecao.getBandeiraURL())
                .placeholder(android.R.drawable.ic_menu_gallery)
                .error(android.R.drawable.stat_notify_error)
                .into(bandeira);

        //@note talvez seja melhor colocar tudo isso nas strings.xml
        info.setText(String.format(
            "Participou de %d copas\n\n" +
            "Ganhou %d copas\n\n" +
            "Estreiou em uma copa do mundo em %d\n\n" +
            "O melhor resultado foi %s",
            selecao.getTotalParticipacoes(),
            selecao.getVezesCampeao(),
            selecao.getAnoEstreia(),
            selecao.getMelhorResultado()
        ));
    }
}
