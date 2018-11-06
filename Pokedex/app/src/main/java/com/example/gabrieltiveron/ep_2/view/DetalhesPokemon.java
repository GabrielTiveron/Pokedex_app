package com.example.gabrieltiveron.ep_2.view;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.gabrieltiveron.ep_2.R;
import com.example.gabrieltiveron.ep_2.model.Movimentos;
import com.example.gabrieltiveron.ep_2.model.PokemonDetalhes;
import com.google.gson.Gson;

import java.util.ArrayList;

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
    private TextView  tipoPokemon1;
    private TextView  tipoPokemon2;
    private RecyclerView recyclerView;
    private AdapterMovimentos adapterMovimentos;
    private ArrayList<Movimentos> movimentosArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_detalhes_pokemon );
        context = this.getApplicationContext();
        final PokemonDetalhes pokemonDetalhes = new PokemonDetalhes();
        int id;
        movimentosArrayList = new ArrayList<>(  );
        adapterMovimentos = new AdapterMovimentos( this );

        intent = getIntent();
        id = intent.getIntExtra( "id", 1 );

        final Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("https://pokeapi.co/api/v2/")
                            .addConverterFactory( GsonConverterFactory.create() )
                            .build();

        pokemonDetalhes.obterDetalhes( retrofit, id );

        frentePadrao    = findViewById( R.id.frentePadrao );
        frenteShiny     = findViewById( R.id.frenteShiny );
        trasPadrao      = findViewById( R.id.trasPadrao );
        trasShiny       = findViewById( R.id.trasShiny );
        nomePokemon     = findViewById( R.id.nomeView );
        recyclerView    = findViewById( R.id.listaMovimentos );
        tipoPokemon1    = findViewById( R.id.tipo1 );
        tipoPokemon2    = findViewById( R.id.tipo2 );

        String id_string = String.valueOf( id );


        recyclerView.setAdapter( adapterMovimentos );
        recyclerView.setLayoutManager( new LinearLayoutManager( getApplicationContext() ) );

        configurarImagens( context, id_string, frentePadrao, "" );
        configurarImagens( context, id_string, trasPadrao, "back/" );
        configurarImagens( context, id_string, frenteShiny, "shiny/" );
        configurarImagens( context, id_string, trasShiny, "back/shiny/" );

        new Handler().postDelayed( new Runnable() {
            @Override
            public void run() {
                nomePokemon.setText( pokemonDetalhes.getName().toUpperCase() );
                tipoPokemon1.setText( pokemonDetalhes.getTypes().get( 0 ).getType().getName() );
                Log.e("Size", " = " + pokemonDetalhes.getMoves().size());


                if(pokemonDetalhes.getTypes().size() > 1){
                    tipoPokemon2.setText( pokemonDetalhes.getTypes().get( 1 ).getType().getName() );
                }

                for(Movimentos m : pokemonDetalhes.getMoves()) {
                    Log.e("ID", " = " + m.getId());
                    m.obterMovimentos( retrofit, m.getId() );
                    movimentosArrayList.add( m );
                }



            }
        } , 5000);
        new Handler().postDelayed( new Runnable() {
            @Override
            public void run() {
                Gson gson = new Gson();

                String a = gson.toJson( movimentosArrayList.get( 0 ) );
                Log.e("GSON", " objeto: " + a);
                adapterMovimentos.adicionarMov( movimentosArrayList );
            }
        }, 13000);

        configurarBotao();


    }

    private void configurarImagens(Context context, String id, ImageView imageView, String complemento){
        Glide.with(context)
                .load("http://pokeapi.co/media/sprites/pokemon/"+ complemento + id + ".png")
                .centerCrop()
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);
    }

    private void configurarBotao(){
        Button btnRetorno = (Button) findViewById( R.id.btnRetorno );

        btnRetorno.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        } );
    }
}
