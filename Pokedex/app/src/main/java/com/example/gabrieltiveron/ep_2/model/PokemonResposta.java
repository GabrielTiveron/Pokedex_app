package com.example.gabrieltiveron.ep_2.model;

import android.util.Log;

import com.example.gabrieltiveron.ep_2.view.AdapterDefault;
import com.example.gabrieltiveron.ep_2.pokeAPI.Servico;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class PokemonResposta {
    private ArrayList<Pokemon> results;


    public ArrayList<Pokemon> getResults() {
        return results;
    }

    public void setResults(ArrayList<Pokemon> results) {
        this.results = results;
    }

    public void obterDados(Retrofit retrofit, final String TAG, final AdapterDefault adapterDefault) {
        Servico servico = retrofit.create( Servico.class );


        Call<PokemonResposta> pokemonResultante = servico.obterListaPokemon();
        Call<PokemonDetalhes> pokemonDetalhesCall = servico.obterDetalhes( 1 );

        pokemonDetalhesCall.enqueue( new Callback<PokemonDetalhes>() {
            @Override
            public void onResponse(Call<PokemonDetalhes> call, Response<PokemonDetalhes> response) {
                if (response.isSuccessful()) {
                    PokemonDetalhes pokemonDetalhes = response.body();
                    Log.e( TAG, " TIPO: " + pokemonDetalhes.getTypes().get( 0 ).getType().getName() );
                    Log.e( TAG, " TIPO: " + pokemonDetalhes.getTypes().get( 1 ).getType().getName() );
                } else {

                }
            }

            @Override
            public void onFailure(Call<PokemonDetalhes> call, Throwable t) {

            }
        } );

        pokemonResultante.enqueue( new Callback<PokemonResposta>() {
            @Override
            public void onResponse(Call<PokemonResposta> call, Response<PokemonResposta> response) {
                if (response.isSuccessful()) {


                    PokemonResposta pokemonResposta = response.body();
                    adapterDefault.Adicionar( pokemonResposta.getResults() );


                } else {
                    Log.e( TAG, " onResponse: " + response.errorBody() );
                }
            }

            @Override
            public void onFailure(Call<PokemonResposta> call, Throwable t) {
                Log.e( TAG, " onFailure: " + t.getMessage() );
            }
        } );
    }
}
