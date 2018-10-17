package com.example.gabrieltiveron.ep_2.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.gabrieltiveron.ep_2.R;
import com.example.gabrieltiveron.ep_2.model.PokemonResposta;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Pokedex extends AppCompatActivity {

    private Retrofit retrofit;
    private static final String TAG = "POKEDEX";
    private RecyclerView recyclerView;
    private AdapterDefault adapterDefault;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_pokedex );
        recyclerView= new RecyclerView( this );

        configurarBotaoRetorno();

        recyclerView = findViewById( R.id.ListinhaMassa );
        adapterDefault = new AdapterDefault(this);
        recyclerView.setAdapter( adapterDefault );
        recyclerView.setHasFixedSize( true );
        GridLayoutManager gridLayoutManager = new GridLayoutManager( this, 3 );
        recyclerView.setLayoutManager( gridLayoutManager );





        retrofit = new Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory( GsonConverterFactory.create() )
                .build();


        PokemonResposta pokemonResposta = new PokemonResposta();

        pokemonResposta.obterDados( retrofit, TAG, adapterDefault );
    }

    private void configurarBotaoRetorno(){
        Button buttonRetorno = (Button) findViewById( R.id.retorno );

        buttonRetorno.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        } );
    }
}
