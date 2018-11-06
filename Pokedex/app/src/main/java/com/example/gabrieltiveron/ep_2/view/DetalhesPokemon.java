package com.example.gabrieltiveron.ep_2.view;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
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
import com.example.gabrieltiveron.ep_2.controller.AdapterMovimentos;
import com.example.gabrieltiveron.ep_2.model.Movimentos;
import com.example.gabrieltiveron.ep_2.model.PokemonDetalhes;

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
                definirBackground(pokemonDetalhes.getTypes().get( 0 ).getType().getName(), true);


                if(pokemonDetalhes.getTypes().size() > 1){
                    tipoPokemon2.setText( pokemonDetalhes.getTypes().get( 1 ).getType().getName() );
                    definirBackground( pokemonDetalhes.getTypes().get( 1 ).getType().getName(), false );
                }

                for(Movimentos m : pokemonDetalhes.getMoves()) {
                    m.obterMovimentos( retrofit, m.getId() );
                    movimentosArrayList.add( m );
                }



            }
        } , 7000);
        new Handler().postDelayed( new Runnable() {
            @Override
            public void run() {
                adapterMovimentos.adicionarMov( movimentosArrayList );

            }
        }, 10000);

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

    private void definirBackground(String tipo, boolean diferencia){

        if(diferencia) {
            switch (tipo) {
                case "normal":
                    tipoPokemon1.setBackground(getResources().getDrawable(R.drawable.tipo_normal));
                    break;
                case "fighting":
                    tipoPokemon1.setBackground(getResources().getDrawable(R.drawable.tipo_fighting));
                    break;
                case "flying":
                    tipoPokemon1.setBackground(getResources().getDrawable(R.drawable.tipo_flying));
                    break;
                case "poison":
                    tipoPokemon1.setBackground(getResources().getDrawable(R.drawable.tipo_poison));
                    break;
                case "ground":
                    tipoPokemon1.setBackground(getResources().getDrawable(R.drawable.tipo_ground));
                    break;
                case "rock":
                    tipoPokemon1.setBackground(getResources().getDrawable(R.drawable.tipo_rock));
                    break;
                case "bug":
                    tipoPokemon1.setBackground(getResources().getDrawable(R.drawable.tipo_bug));
                    break;
                case "ghost":
                    tipoPokemon1.setBackground(getResources().getDrawable(R.drawable.tipo_ghost));
                    break;
                case "steel":
                    tipoPokemon1.setBackground(getResources().getDrawable(R.drawable.tipo_steel));
                    break;
                case "fire":
                    tipoPokemon1.setBackground(getResources().getDrawable(R.drawable.tipo_fire));
                    break;
                case "water":
                    tipoPokemon1.setBackground(getResources().getDrawable(R.drawable.tipo_water));
                    break;
                case "grass":
                    tipoPokemon1.setBackground(getResources().getDrawable(R.drawable.tipo_grass));
                    break;
                case "electric":
                    tipoPokemon1.setBackground(getResources().getDrawable(R.drawable.tipo_eletric));
                    break;
                case "psychic":
                    tipoPokemon1.setBackground(getResources().getDrawable(R.drawable.tipo_psychic));
                    break;
                case "ice":
                    tipoPokemon1.setBackground(getResources().getDrawable(R.drawable.tipo_ice));
                    break;
                case "dragon":
                    tipoPokemon1.setBackground(getResources().getDrawable(R.drawable.tipo_dragon));
                    break;
                case "dark":
                    tipoPokemon1.setBackground(getResources().getDrawable(R.drawable.tipo_dark));
                    break;
                case "fairy":
                    tipoPokemon1.setBackground(getResources().getDrawable(R.drawable.tipo_fairy));
                    break;
                case "shadow":
                    tipoPokemon1.setBackground(getResources().getDrawable(R.drawable.tipo_shadow));
                    break;
                default:
                    tipoPokemon1.setBackground(getResources().getDrawable(R.drawable.tipo_normal));
                    break;

            }
        } else {
            switch (tipo) {
                case "normal":
                    tipoPokemon2.setBackground(getResources().getDrawable(R.drawable.tipo_normal));
                    break;
                case "fighting":
                    tipoPokemon2.setBackground(getResources().getDrawable(R.drawable.tipo_fighting));
                    break;
                case "flying":
                    tipoPokemon2.setBackground(getResources().getDrawable(R.drawable.tipo_flying));
                    break;
                case "poison":
                    tipoPokemon2.setBackground(getResources().getDrawable(R.drawable.tipo_poison));
                    break;
                case "ground":
                    tipoPokemon2.setBackground(getResources().getDrawable(R.drawable.tipo_ground));
                    break;
                case "rock":
                    tipoPokemon2.setBackground(getResources().getDrawable(R.drawable.tipo_rock));
                    break;
                case "bug":
                    tipoPokemon2.setBackground(getResources().getDrawable(R.drawable.tipo_bug));
                    break;
                case "ghost":
                    tipoPokemon2.setBackground(getResources().getDrawable(R.drawable.tipo_ghost));
                    break;
                case "steel":
                    tipoPokemon2.setBackground(getResources().getDrawable(R.drawable.tipo_steel));
                    break;
                case "fire":
                    tipoPokemon2.setBackground(getResources().getDrawable(R.drawable.tipo_fire));
                    break;
                case "water":
                    tipoPokemon2.setBackground(getResources().getDrawable(R.drawable.tipo_water));
                    break;
                case "grass":
                    tipoPokemon2.setBackground(getResources().getDrawable(R.drawable.tipo_grass));
                    break;
                case "electric":
                    tipoPokemon2.setBackground(getResources().getDrawable(R.drawable.tipo_eletric));
                    break;
                case "psychic":
                    tipoPokemon2.setBackground(getResources().getDrawable(R.drawable.tipo_psychic));
                    break;
                case "ice":
                    tipoPokemon2.setBackground(getResources().getDrawable(R.drawable.tipo_ice));
                    break;
                case "dragon":
                    tipoPokemon2.setBackground(getResources().getDrawable(R.drawable.tipo_dragon));
                    break;
                case "dark":
                    tipoPokemon2.setBackground(getResources().getDrawable(R.drawable.tipo_dark));
                    break;
                case "fairy":
                    tipoPokemon2.setBackground(getResources().getDrawable(R.drawable.tipo_fairy));
                    break;
                case "shadow":
                    tipoPokemon2.setBackground(getResources().getDrawable(R.drawable.tipo_shadow));
                    break;
                default:
                    tipoPokemon2.setBackground(getResources().getDrawable(R.drawable.tipo_normal));
                    break;

            }
        }
    }
}
