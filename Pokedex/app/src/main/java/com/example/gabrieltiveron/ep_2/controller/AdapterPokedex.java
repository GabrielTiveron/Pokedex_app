package com.example.gabrieltiveron.ep_2.controller;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.gabrieltiveron.ep_2.R;
import com.example.gabrieltiveron.ep_2.model.Movimentos;
import com.example.gabrieltiveron.ep_2.model.Pokemon;
import com.example.gabrieltiveron.ep_2.model.PokemonDetalhes;
import com.example.gabrieltiveron.ep_2.view.DetalhesPokemon;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.logging.Handler;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AdapterPokedex extends RecyclerView.Adapter<AdapterPokedex.ViewHolder> {

    private ArrayList<Pokemon> pokemons;
    private ArrayList<Pokemon> listaPokemon;
    private Context context;
    private Intent intent;
    private ArrayList<PokemonDetalhes> pokemonDetalhes;


    public AdapterPokedex(Context context) {
        pokemons = new ArrayList<>();
        this.context = context;
        listaPokemon = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from( parent.getContext() ).inflate( R.layout.adapter_lista_pokedex, parent, false );
        return new ViewHolder( view );
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Pokemon aux = pokemons.get( position );
        holder.textView.setText( aux.getName() );
        holder.imageView.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent( context, DetalhesPokemon.class );
                intent.putExtra( "id", aux.getNumber() );
                intent.putExtra( "nome", aux.getName() );
                context.startActivity( intent );
            }
        } );

        Glide.with( context )
                .load( "http://pokeapi.co/media/sprites/pokemon/" + aux.getNumber() + ".png" )
                .centerCrop()
                .crossFade()
                .diskCacheStrategy( DiskCacheStrategy.ALL )
                .into( holder.imageView );


    }

    @Override
    public int getItemCount() {
        return pokemons.size();
    }

    public void Adicionar(ArrayList<Pokemon> results) {
        pokemons = new ArrayList<>();
        pokemons.addAll( results );
        listaPokemon.addAll( results );
        notifyDataSetChanged();
    }

    public void AdicionarDetalhes(ArrayList<PokemonDetalhes> pDetalhes) {
        ArrayList<Pokemon> p = new ArrayList<>();


        for(PokemonDetalhes pd : pDetalhes) {
            Pokemon pk = new Pokemon();
            pk.setNumber( pd.getId() );
            pk.setName( pd.getName() );
            pk.setUrl( "/"+pd.getId()+"/" );
            p.add(pk);
        }
        pokemons = new ArrayList<>(  );
        Collections.sort( p, new Comparator<Pokemon>() {
            @Override
            public int compare(Pokemon o1, Pokemon o2) {
                return o1.getNumber() - o2.getNumber();
            }
        } );
        pokemons.addAll( p );
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView textView;

        public ViewHolder(View itemView) {
            super( itemView );
            textView = itemView.findViewById( R.id.Texto );
            imageView = itemView.findViewById( R.id.Imagem );
        }


    }

    public void filtrar(String nomePokemon) {
        nomePokemon = nomePokemon.toLowerCase();
        pokemons.clear();

        for (Pokemon p : listaPokemon) {
            if (p.getName().toLowerCase().contains( nomePokemon )) {
                pokemons.add( p );
            }
        }
        notifyDataSetChanged();
    }


    public void AdicionarTipo(final String tipo, ArrayList<PokemonDetalhes> pDetalhes) {
        ArrayList<PokemonDetalhes> pokemonDetalhes = new ArrayList<>(  );

        if(tipo.equals( "padrão" )){
            this.Adicionar( listaPokemon );
        }else {

            for (PokemonDetalhes pd : pDetalhes) {
                // Log.e("pd", "= " + pd.getTypes().get( 0 ).getType().getName());

                if (pd.getTypes().get( 0 ).getType().getName().equals( tipo )) {
                    pokemonDetalhes.add( pd );
                }
                if (pd.getTypes().size() == 2) {
                    if (pd.getTypes().get( 1 ).getType().getName().equals( tipo )) {
                        pokemonDetalhes.add( pd );
                    }
                }
            }

            this.AdicionarDetalhes( pokemonDetalhes );
        }

    }

}
