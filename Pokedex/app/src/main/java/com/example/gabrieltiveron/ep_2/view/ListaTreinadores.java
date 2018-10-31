package com.example.gabrieltiveron.ep_2.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.gabrieltiveron.ep_2.R;
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

        ArrayList<Treinador> treinadores = new ArrayList<>( Arrays.asList(sPreferences.getTreinadores()) );


        recyclerView = findViewById( R.id.listaTreinador );
        recyclerView.setLayoutManager( new LinearLayoutManager( getApplicationContext() ) );
        recyclerView.setAdapter( adapterListaTreinadores );


        adapterListaTreinadores.adicionar( treinadores );




        Log.e("ASDGTERE", "dvdvsdv");


    }
}
