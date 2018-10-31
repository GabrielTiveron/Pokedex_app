package com.example.gabrieltiveron.ep_2.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.Button;

import com.example.gabrieltiveron.ep_2.R;
import com.example.gabrieltiveron.ep_2.model.Pokemon;
import com.example.gabrieltiveron.ep_2.model.PokemonResposta;

import java.util.ArrayList;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Pokedex extends AppCompatActivity {

    private Retrofit retrofit;
    private static final String TAG = "POKEDEX";
    private RecyclerView recyclerView;
    private AdapterPokedex adapterPokedex;
    private SearchView searchView;
    private ArrayList<Pokemon> pokemons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_pokedex );
        recyclerView= new RecyclerView( this );
        searchView = findViewById( R.id.pesquisa );



        recyclerView = findViewById( R.id.pokedexList );
        adapterPokedex = new AdapterPokedex(this);
        recyclerView.setAdapter( adapterPokedex );
        recyclerView.setHasFixedSize( true );
        GridLayoutManager gridLayoutManager = new GridLayoutManager( this, 3 );
        recyclerView.setLayoutManager( gridLayoutManager );


        retrofit = new Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory( GsonConverterFactory.create() )
                .build();


        PokemonResposta pokemonResposta = new PokemonResposta();

        pokemonResposta.obterDados( retrofit, TAG, adapterPokedex, pokemons );

        configurarBotaoRetorno();
        searchView.setOnQueryTextListener( new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapterPokedex.filtrar( newText );

                return true;
            }
        } );
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

//    @Override
//    public boolean onQueryTextSubmit(String query) {
//        return false;
//    }
//
//    @Override
//    public boolean onQueryTextChange(String newText) {
//
//        return false;
//    }
}
