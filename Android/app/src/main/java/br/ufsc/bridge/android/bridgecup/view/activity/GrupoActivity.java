package br.ufsc.bridge.android.bridgecup.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import br.ufsc.bridge.android.bridgecup.R;

public class GrupoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grupo);

        // utiliza a Intent que iniciou essa activity e extrai a string "GRUPO" enviada
        Intent intent = getIntent();
        String message = intent.getStringExtra("GRUPO");
        Toast.makeText(getApplicationContext(), "Grupo " + message, Toast.LENGTH_SHORT).show();
    }
}
