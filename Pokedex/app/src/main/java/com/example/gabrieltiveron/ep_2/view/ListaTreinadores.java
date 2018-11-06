package com.example.gabrieltiveron.ep_2.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.gabrieltiveron.ep_2.R;
import com.example.gabrieltiveron.ep_2.controller.AdapterListaTreinadores;
import com.example.gabrieltiveron.ep_2.helper.TreinadoresPreferences;
import com.example.gabrieltiveron.ep_2.model.Treinador;

import java.util.ArrayList;
import java.util.Arrays;

public class ListaTreinadores extends AppCompatActivity {

    private AdapterListaTreinadores adapterListaTreinadores;
    private RecyclerView recyclerView;
    private TreinadoresPreferences sPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_lista_treinadores );
        adapterListaTreinadores = new AdapterListaTreinadores( this );
        sPreferences = new TreinadoresPreferences( getApplicationContext() );

        if(sPreferences.getTreinadores(  ) != null) {

            ArrayList<Treinador> treinadores = new ArrayList<>( Arrays.asList( sPreferences.getTreinadores() ) );


            recyclerView = findViewById( R.id.listaTreinador );
            recyclerView.setLayoutManager( new LinearLayoutManager( getApplicationContext() ) );
            recyclerView.setAdapter( adapterListaTreinadores );


            adapterListaTreinadores.adicionar( treinadores );
        }

        configurarBotaoRetorno();

    }

    private void configurarBotaoRetorno() {
        Button btnRetorno = (Button) findViewById( R.id.btnRetorno );

        btnRetorno.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        } );
    }


}
