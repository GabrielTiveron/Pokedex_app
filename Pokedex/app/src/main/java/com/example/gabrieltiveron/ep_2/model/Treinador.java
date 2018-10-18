package com.example.gabrieltiveron.ep_2.model;

import java.util.ArrayList;

public class Treinador {
    private ArrayList<Pokemon> pokemons;
    private String nome;

    public Treinador(ArrayList<Pokemon> pokemons, String nome) {
        this.pokemons = pokemons;
        this.nome = nome;
    }

    public ArrayList<Pokemon> getPokemons() {
        return pokemons;
    }

    public void setPokemons(ArrayList<Pokemon> pokemons) {
        this.pokemons = pokemons;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
