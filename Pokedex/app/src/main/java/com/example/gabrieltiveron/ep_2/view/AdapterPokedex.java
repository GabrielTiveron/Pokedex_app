package com.example.gabrieltiveron.ep_2.view;

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
import com.example.gabrieltiveron.ep_2.model.Pokemon;

import java.util.ArrayList;
import java.util.List;

public class AdapterPokedex extends RecyclerView.Adapter<AdapterPokedex.ViewHolder> {

    private ArrayList<Pokemon> pokemons;
    private ArrayList<Pokemon> listaPokemon;
    private Context context;
    private Intent intent;


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
        pokemons.addAll( results );
        listaPokemon.addAll( results );
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


        Log.e( "Entrada", "pro paraiso" );
        for (Pokemon p : listaPokemon) {
            if (p.getName().toLowerCase().contains( nomePokemon )) {
                pokemons.add( p );
            }
        }
        notifyDataSetChanged();
    }
}
