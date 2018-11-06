package com.example.gabrieltiveron.ep_2.model;

import android.util.Log;

import com.example.gabrieltiveron.ep_2.pokeAPI.Servico;
import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class PokemonDetalhes {
    private ArrayList<Tipo> types;
    private String name;
    private ArrayList<Movimentos> moves;

    public ArrayList<Movimentos> getMoves() {
        return moves;
    }

    public void setMoves(ArrayList<Movimentos> moves) {
        this.moves = moves;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Tipo> getTypes() {
        return types;
    }

    public void setTypes(ArrayList<Tipo> types) {
        this.types = types;
    }


    public void obterDetalhes(final Retrofit retrofit, int id){

        Servico servico = retrofit.create( Servico.class );

        final Call<PokemonDetalhes> pokemonDetalhesCall = servico.obterDetalhes( id );

        pokemonDetalhesCall.enqueue( new Callback<PokemonDetalhes>() {
            @Override
            public void onResponse(Call<PokemonDetalhes> call, Response<PokemonDetalhes> response) {
                if(response.isSuccessful()){
                    PokemonDetalhes aux = response.body();
                    setAll(aux);


                }else{
                    Log.e("OBTER DETALHES", "onResponse");
                    response.raw().body().close();
                }
            }

            @Override
            public void onFailure(Call<PokemonDetalhes> call, Throwable t) {
                Log.e("OBTER DETALHES", "onFailure" + t.getMessage());
            }
        } );

    }

    private void setAll(PokemonDetalhes pokemonDetalhes){

        this.setName( pokemonDetalhes.getName() );
        this.setTypes( pokemonDetalhes.getTypes() );
        this.setMoves( pokemonDetalhes.getMoves() );

    }

}
