package br.ufsc.bridge.android.bridgecup.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import br.ufsc.bridge.android.bridgecup.R;
import br.ufsc.bridge.android.bridgecup.model.entity.Selecao;

public class SelecaoActivity extends AppCompatActivity {

    private Selecao selecao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selecao);

        //utiliza a Intent que iniciou essa activity e extrai dela a Selecao enviada
        Intent intent = getIntent();
        selecao = (Selecao) intent.getSerializableExtra("SELECAO");

        //cria referencias ao layout
        ImageView bandeira = findViewById(R.id.iv_bandeira);
        TextView info = findViewById(R.id.tv_info_selecao);

        //preenche o layout
        Picasso.get()
                .load(selecao.getBandeiraURL())
                .placeholder(android.R.drawable.ic_menu_gallery)
                .error(android.R.drawable.stat_notify_error)
                .into(bandeira);

        info.setText(String.format("Participou de %d copas\n\n" +
                                    "Ganhou %d copas\n\n" +
                                    "Estreiou em uma copa do mundo em %d\n\n" +
                                    "O melhor resultado foi %s",
                                    selecao.getTotalParticipacoes(),
                                    selecao.getVezesCampeao(),
                                    selecao.getAnoEstreia(),
                                    selecao.getMelhorResultado()));
    }
}
