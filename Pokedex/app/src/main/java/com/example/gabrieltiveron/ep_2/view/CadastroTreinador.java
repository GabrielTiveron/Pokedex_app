package com.example.gabrieltiveron.ep_2.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.gabrieltiveron.ep_2.R;
import com.example.gabrieltiveron.ep_2.model.Pokemon;
import com.example.gabrieltiveron.ep_2.model.PokemonResposta;
import com.example.gabrieltiveron.ep_2.model.Treinador;

import java.util.ArrayList;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CadastroTreinador extends AppCompatActivity {


    private Retrofit retrofit;
    private static final String TAG = "POKEDEX";
    private RecyclerView recyclerView;
    private AdapterListaCadastro adapterDefault;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_cadastro_treinador );

        recyclerView = new RecyclerView( this );

        recyclerView = findViewById( R.id.recyclerView );
        adapterDefault = new AdapterListaCadastro(this);
        recyclerView.setAdapter( adapterDefault );
        recyclerView.setHasFixedSize( true );
        GridLayoutManager gridLayoutManager = new GridLayoutManager( this, 1 );
        recyclerView.setLayoutManager( gridLayoutManager );

        retrofit = new Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory( GsonConverterFactory.create() )
                .build();


        configurarBotaoRetorno();
        configurarBotaoAdicionar();
        configurarBotaoRegistro();


    }

    private void configurarBotaoRetorno(){
        Button buttonRetorno = (Button) findViewById( R.id.retornar );

        buttonRetorno.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        } );
    }

    private void configurarBotaoAdicionar(){
        Button buttonAdicionar = (Button) findViewById( R.id.adicionar );

        buttonAdicionar.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText editText = findViewById( R.id.pokemonEdit );


                PokemonResposta pokemonResposta = new PokemonResposta();

                if(editText.getText().toString().compareTo( "" ) != 0) {
                    pokemonResposta.obterDados( retrofit, TAG, adapterDefault, editText.getText().toString().toLowerCase() );
                    editText.setText( "" );
                }

            }
        } );
    }

    private void configurarBotaoRegistro(){
        Button buttonRegistro = (Button) findViewById( R.id.registrar );

        buttonRegistro.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText editText = findViewById( R.id.nomeEdit );

                ArrayList<Pokemon> pokemon = adapterDefault.getPokemon();
                Treinador treinador;

                if(editText.getText().toString().compareTo( "" ) != 0) {
                    treinador = new Treinador( pokemon, editText.getText().toString() );
                    finish();
                }

            }
        } );
    }
}
