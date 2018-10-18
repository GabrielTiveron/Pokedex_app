package com.example.gabrieltiveron.ep_2.view;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.gabrieltiveron.ep_2.R;
import com.example.gabrieltiveron.ep_2.model.PokemonDetalhes;
import com.example.gabrieltiveron.ep_2.model.PokemonResposta;
import com.example.gabrieltiveron.ep_2.pokeAPI.Servico;

import retrofit2.Call;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_detalhes_pokemon );
        context = this.getApplicationContext();
        PokemonDetalhes pokemonDetalhes = new PokemonDetalhes();
        String id;

        intent = getIntent();
        id = intent.getStringExtra( "id" );

        Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("https://pokeapi.co/api/v2/")
                            .addConverterFactory( GsonConverterFactory.create() )
                            .build();

        pokemonDetalhes.obterDetalhes(retrofit, "OBTER DETALHES", id);
        


        frentePadrao = findViewById( R.id.frentePadrao );
        frenteShiny = findViewById( R.id.frenteShiny );
        trasPadrao = findViewById( R.id.trasPadrao );
        trasShiny = findViewById( R.id.trasShiny );
        nomePokemon = findViewById( R.id.nomeView );


        configurarImagens( context, id, frentePadrao, "" );
        configurarImagens( context, id, trasPadrao, "back/" );
        configurarImagens( context, id, frenteShiny, "shiny/" );
        configurarImagens( context, id, trasShiny, "back/shiny/" );

        nomePokemon.setText( pokemonDetalhes.getName() );
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
