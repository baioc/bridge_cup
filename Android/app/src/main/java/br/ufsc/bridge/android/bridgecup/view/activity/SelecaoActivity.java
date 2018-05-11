package br.ufsc.bridge.android.bridgecup.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import br.ufsc.bridge.android.bridgecup.R;
import br.ufsc.bridge.android.bridgecup.model.client.WorldCupApi;
import br.ufsc.bridge.android.bridgecup.model.client.WorldCupApiUtil;
import br.ufsc.bridge.android.bridgecup.model.entity.Selecao;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SelecaoActivity extends AppCompatActivity {

    private Selecao selecao = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selecao);

        //cria referencias ao layout
        ImageView bandeira = findViewById(R.id.iv_bandeira);
        TextView info = findViewById(R.id.tv_info_selecao);

        //utiliza a Intent que iniciou essa activity e extrai dela a Selecao enviada
        Intent intent = getIntent();
        selecao = (Selecao) intent.getSerializableExtra("SELECAO");
        String sigla = (String) intent.getSerializableExtra("SIG_SELECAO");

        //dependendo de como a activity foi iniciada, decide se deve carregar a selecao da api
        if (selecao == null) {
            connectAndFillSelecao(sigla);
        }

        /*//preenche o layout
        Picasso.get()
                .load(selecao.getBandeiraURL())
                .placeholder(android.R.drawable.ic_menu_gallery)
                .error(android.R.drawable.stat_notify_error)
                .into(bandeira);

        //@todo colocar tudo isso nas strings.xml
        info.setText(String.format("Participou de %d copas\n\n" +
                                    "Ganhou %d copas\n\n" +
                                    "Estreiou em uma copa do mundo em %d\n\n" +
                                    "O melhor resultado foi %s",
                                    selecao.getTotalParticipacoes(),
                                    selecao.getVezesCampeao(),
                                    selecao.getAnoEstreia(),
                                    selecao.getMelhorResultado()));*/
    }


    /** Preenche a Selecao dessa classe utilizando a API REST */
    private void connectAndFillSelecao(String sigla){
        WorldCupApi api = WorldCupApiUtil.getClient();//conecta a api
        Call<Selecao> selecaoCall = api.getSelecao(sigla);///GET Selecao

        //utiliza a resposta da api (processada em uma thread no background)
        selecaoCall.enqueue( new Callback<Selecao>() {
            @Override
            public void onResponse(Call<Selecao> call, Response<Selecao> response) {
                selecao = response.body();
                Toast.makeText(getApplicationContext(), "LOADED " + selecao.getSigla(), Toast.LENGTH_SHORT).show();//@fixme travando ao abrir selecao (aplicar fix no grupoactivity tambem)
            }

            @Override
            public void onFailure(Call<Selecao>  call, Throwable throwable) {
                Toast.makeText(getApplicationContext(), getString(R.string.load_error), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
