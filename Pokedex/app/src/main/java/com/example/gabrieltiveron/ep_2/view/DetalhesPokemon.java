package com.example.gabrieltiveron.ep_2.view;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.gabrieltiveron.ep_2.R;
import com.example.gabrieltiveron.ep_2.model.PokemonDetalhes;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetalhesPokemon extends AppCompatActivity {

    private Intent intent;
    private Context context;
    private ImageView trasShiny;
    private ImageView frentePadrao;
    private ImageView frenteShiny;
    private ImageView trasPadrao;
    private TextView  nomePokemon;
    private TextView  nomeEspecie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_detalhes_pokemon );
        context = this.getApplicationContext();
        final PokemonDetalhes pokemonDetalhes = new PokemonDetalhes();
        int id;

        intent = getIntent();
        id = intent.getIntExtra( "id", 1 );

        Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("https://pokeapi.co/api/v2/")
                            .addConverterFactory( GsonConverterFactory.create() )
                            .build();

        pokemonDetalhes.obterDetalhes( retrofit, id );



        frentePadrao    = findViewById( R.id.frentePadrao );
        frenteShiny     = findViewById( R.id.frenteShiny );
        trasPadrao      = findViewById( R.id.trasPadrao );
        trasShiny       = findViewById( R.id.trasShiny );
        nomePokemon     = findViewById( R.id.nomeView );
        nomeEspecie     = findViewById( R.id.textEspecie );

        String id_string = String.valueOf( id );


        configurarImagens( context, id_string, frentePadrao, "" );
        configurarImagens( context, id_string, trasPadrao, "back/" );
        configurarImagens( context, id_string, frenteShiny, "shiny/" );
        configurarImagens( context, id_string, trasShiny, "back/shiny/" );

        new Handler().postDelayed( new Runnable() {
            @Override
            public void run() {
                nomePokemon.setText( pokemonDetalhes.getName().toUpperCase() );
                nomeEspecie.setText( pokemonDetalhes.getSpecies().getName() );

            }
        } , 10000);

//        Log.e( "DADOS", " Especie: " + pokemonDetalhes.getSpecies().getName() );

    }

    private void configurarImagens(Context context, String id, ImageView imageView, String complemento){
        Glide.with(context)
                .load("http://pokeapi.co/media/sprites/pokemon/"+ complemento + id + ".png")
                .centerCrop()
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);
    }
}
