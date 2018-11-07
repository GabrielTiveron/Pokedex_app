package com.example.gabrieltiveron.ep_2.view;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.gabrieltiveron.ep_2.R;
import com.example.gabrieltiveron.ep_2.controller.AdapterPokedex;
import com.example.gabrieltiveron.ep_2.model.Pokemon;
import com.example.gabrieltiveron.ep_2.model.PokemonDetalhes;
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
    private ArrayList<PokemonDetalhes> pokemonDetalhes;
    private PokemonDetalhes pDetalhes;
    private PokemonResposta pokemonRespostas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_pokedex );
        recyclerView= new RecyclerView( this );
        searchView = findViewById( R.id.pesquisa );
        pDetalhes = new PokemonDetalhes();
        pokemonDetalhes = new ArrayList<>(  );
        pokemonRespostas = new PokemonResposta();



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


        final PokemonResposta pokemonResposta = new PokemonResposta();

        pokemonResposta.obterDados( retrofit, TAG, adapterPokedex, pokemonRespostas );

        new Handler(  ).postDelayed( new Runnable() {
            @Override
            public void run() {


                for(Pokemon p : pokemonRespostas.getResults()) {
                    Log.e( "Aqui", " = " + p.getNumber() );
                    pDetalhes.obterDetalhes( retrofit, p.getNumber(), pokemonDetalhes );
//                    new Handler().postDelayed( new Runnable() {
//                        @Override
//                        public void run() {
//                            pokemonDetalhes.add( pDetalhes );
//                        }
//                    }, 1000 );
                }
            }
        }, 20000 );

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

        configurarBotaoRetorno();
        configurarBotaoMenu();
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

    private void configurarBotaoMenu(){
        Button btnMenu = (Button) findViewById( R.id.btnMenu );

        btnMenu.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popUpMenu(v);
            }
        } );
    }

    private void popUpMenu(View v){
        PopupMenu popup = new PopupMenu(this, v);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu, popup.getMenu());
        popup.show();

        popup.setOnMenuItemClickListener( new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {


                if(pokemonDetalhes.size() >= 940) {
                    adapterPokedex.AdicionarTipo( item.getTitle().toString().toLowerCase(), pokemonDetalhes );
                } else {
                    Toast.makeText( getApplicationContext(), "Espere até que todos os pokemons sejam carregados", Toast.LENGTH_SHORT ).show();
                }
                return true;
            }
        });
    }
//   15:43:17.985
//   15:45:24.127
}
