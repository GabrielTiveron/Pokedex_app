package com.example.gabrieltiveron.ep_2.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.gabrieltiveron.ep_2.R;
import com.example.gabrieltiveron.ep_2.helper.TreinadoresPreferences;
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
    private AdapterListaCadastro adapterListaCadastro;
    private TreinadoresPreferences sPreferences;
    private Treinador treinador;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_cadastro_treinador );

        treinador = new Treinador(  );

        recyclerView = new RecyclerView( this );

        recyclerView         = findViewById( R.id.recyclerView );
        adapterListaCadastro = new AdapterListaCadastro(this);
        recyclerView.setAdapter( adapterListaCadastro );
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
        configurarImagens();


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
                    pokemonResposta.obterDados( retrofit, TAG, adapterListaCadastro, editText.getText().toString().toLowerCase() );
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

                ArrayList<Pokemon> pokemon = adapterListaCadastro.getPokemon();
                sPreferences = new TreinadoresPreferences( getApplicationContext() );


                if(adapterListaCadastro.getPokemon().size() == 0 || editText.getText().toString().compareTo( "" ) == 0 || treinador.getSexo() == null) {
                    Toast.makeText(getApplicationContext(), "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
                }
                else {

                    treinador = new Treinador( pokemon, editText.getText().toString() );

                    sPreferences.salvarTreinador( treinador );

                    Toast.makeText( getApplicationContext(), "Treinador registrado com sucesso!", Toast.LENGTH_SHORT ).show();

                    finish();
                }

            }
        } );
    }

    private void configurarImagens(){
        final ImageView ashView = findViewById( R.id.ashView );
        final ImageView mayView = findViewById( R.id.mayView );


        ashView.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ashView.setBackgroundColor( getResources().getColor( R.color.branco ) );
                mayView.setBackgroundColor( getResources().getColor( R.color.transparent ) );
                treinador.setSexo( "HOMEM" );

            }
        } );

        mayView.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mayView.setBackgroundColor( getResources().getColor( R.color.branco ) );
                ashView.setBackgroundColor( getResources().getColor( R.color.transparent ) );
                treinador.setSexo( "MULHER" );
            }
        } );
    }

}
