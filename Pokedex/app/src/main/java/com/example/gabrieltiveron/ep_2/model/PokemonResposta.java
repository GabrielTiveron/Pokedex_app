package com.example.gabrieltiveron.ep_2.model;

import android.util.Log;

import com.example.gabrieltiveron.ep_2.controller.AdapterPokedex;
import com.example.gabrieltiveron.ep_2.pokeAPI.Servico;
import com.example.gabrieltiveron.ep_2.controller.AdapterListaCadastro;

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

    public void obterDados(Retrofit retrofit, final String TAG, final AdapterPokedex adapterPokedex) {
        Servico servico = retrofit.create( Servico.class );


        Call<PokemonResposta> pokemonResultante = servico.obterListaPokemon();

        pokemonResultante.enqueue( new Callback<PokemonResposta>() {
            @Override
            public void onResponse(Call<PokemonResposta> call, Response<PokemonResposta> response) {
                if (response.isSuccessful()) {

                    PokemonResposta pokemonResposta = response.body();
                    adapterPokedex.Adicionar( pokemonResposta.getResults() );


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

    public void obterDados(Retrofit retrofit, final String TAG, final AdapterListaCadastro adapterListaCadastro, final String nome) {
        Servico servico = retrofit.create( Servico.class );


        Call<PokemonResposta> pokemonResultante = servico.obterListaPokemon();

        pokemonResultante.enqueue( new Callback<PokemonResposta>() {
            @Override
            public void onResponse(Call<PokemonResposta> call, Response<PokemonResposta> response) {
                if(response.isSuccessful()){
                    PokemonResposta pokemonResposta = response.body();

                    adapterListaCadastro.adicionar( pokemonResposta.getResults(), nome );
                }
            }

            @Override
            public void onFailure(Call<PokemonResposta> call, Throwable t) {
                Log.e( TAG, " onFailure: " + t.getMessage() );
            }
        } );
    }


}
