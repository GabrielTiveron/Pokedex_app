package com.example.gabrieltiveron.ep_2.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.gabrieltiveron.ep_2.R;
import com.example.gabrieltiveron.ep_2.model.Pokemon;
import com.example.gabrieltiveron.ep_2.model.Treinador;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Treinador> treinadores;
    private Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        configurarBotaoCadastro();
        configurarBotaoPokedex();
        configurarBotaoTreinadores();


    }

    private void configurarBotaoTreinadores(){
        Button buttonListaTreinadores = (Button) findViewById( R.id.btnListaTreinadores );

        buttonListaTreinadores.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent( MainActivity.this, ListaTreinadores.class ) );
            }
        } );
    }

    private void configurarBotaoPokedex(){
        Button buttonCadastro = (Button) findViewById( R.id.pokedex );

        buttonCadastro.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Pokedex.class ));
            }
        } );
    }

    private void configurarBotaoCadastro(){
        Button buttonCadastro = (Button) findViewById( R.id.cadastro );

        buttonCadastro.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CadastroTreinador.class ));
            }
        } );
    }

    private void instanciarTreinador(Bundle bundle){
        ArrayList<Pokemon> pokemons = new ArrayList<>(  );
        Pokemon pokemon = new Pokemon();
        for (String numero : bundle.keySet()){
            pokemon.setName( bundle.getString("nome") );
            pokemon.setNumber( bundle.getInt("numero") );
            pokemon.setUrl( bundle.getString("url") );
            pokemons.add( pokemon );
            Log.e("CADASTRO:", " Pokemon: " + pokemon.getName());
        }

        Treinador treinador = new Treinador( pokemons, bundle.getString( "nomeTreinador" ) );
        Log.e("CADASTRO", " Treinador: " + treinador.getNome());

        treinadores.add( treinador );
    }

}
