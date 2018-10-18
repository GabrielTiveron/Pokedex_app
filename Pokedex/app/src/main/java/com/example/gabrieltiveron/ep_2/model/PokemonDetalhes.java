package com.example.gabrieltiveron.ep_2.model;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.gabrieltiveron.ep_2.pokeAPI.Servico;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class PokemonDetalhes {
    private ArrayList<Tipo> types;
    private String name;
    //private String species;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public String getSpecies() {
//        return species;
//    }
//
//    public void setSpecies(String species) {
//        this.species = species;
//    }

    public ArrayList<Tipo> getTypes() {
        return types;
    }

    public void setTypes(ArrayList<Tipo> types) {
        this.types = types;
    }

    public class Tipo
    {
        private Tipagem type;

        public Tipagem getType() {
            return type;
        }

        public void setType(Tipagem type) {
            this.type = type;
        }
    }

    public class Tipagem{
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }


    public void obterDetalhes(Retrofit retrofit, final String TAG, String id){

        Servico servico = retrofit.create( Servico.class );
        final Intent intent = new Intent(  );

        Call<PokemonDetalhes> pokemonDetalhesCall = servico.obterDetalhes( id );

        pokemonDetalhesCall.enqueue( new Callback<PokemonDetalhes>() {
            @Override
            public void onResponse(Call<PokemonDetalhes> call, Response<PokemonDetalhes> response) {
                if(response.isSuccessful()){
                    PokemonDetalhes pokemon = response.body();

                    intent.putExtra( "nome", pokemon.getName() );
                    intent.putExtra( "tipo", pokemon.getTypes().get( 0 ).getType().getName() );
                    Log.e( TAG, " Será? " + intent.getStringExtra( "nome" ) );


                }else{
                    Log.e(TAG, "onResponse");
                }
            }

            @Override
            public void onFailure(Call<PokemonDetalhes> call, Throwable t) {
                Log.e(TAG, "onFailure" + t.getMessage());
            }
        } );

        this.setName( intent.getStringExtra( "nome" ) );
        Log.e( TAG, " Será? " + intent.getStringExtra( "nome" ) );
        //this.setTypes( intent.getStringExtra( "tipo" ) );

    }
}
