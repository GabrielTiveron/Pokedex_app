package com.example.gabrieltiveron.ep_2.pokeAPI;

import com.example.gabrieltiveron.ep_2.model.Movimentos;
import com.example.gabrieltiveron.ep_2.model.PokemonDetalhes;
import com.example.gabrieltiveron.ep_2.model.PokemonResposta;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Servico {

    @GET("pokemon")
    Call<PokemonResposta> obterListaPokemon();

    @GET("pokemon/{id}")
    Call<PokemonDetalhes> obterDetalhes(@Path( "id" ) int id);

    @GET("move/{id}")
    Call<Movimentos> obterMovimento(@Path( "id" ) int id);

}
