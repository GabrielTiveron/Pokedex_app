package com.example.gabrieltiveron.ep_2.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
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

public class AdapterDefault  extends RecyclerView.Adapter<AdapterDefault.ViewHolder>{

    private ArrayList<Pokemon> pokemons;
    private Context context;

    public AdapterDefault(Context context) {
        pokemons = new ArrayList<>(  );
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from( parent.getContext() ).inflate(R.layout.pokemon_default, parent, false);
        return new ViewHolder( view );
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Pokemon aux = pokemons.get( position );
        holder.textView.setText( aux.getName() );

        Glide.with(context)
            .load("http://pokeapi.co/media/sprites/pokemon/" + aux.getNumber() + ".png")
            .centerCrop()
            .crossFade()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return pokemons.size();
    }

    public void Adicionar(ArrayList<Pokemon> results) {
        pokemons.addAll(results);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView imageView;
        private TextView textView;

        public ViewHolder(View itemView) {
            super( itemView );
            textView = itemView.findViewById( R.id.Texto );
            imageView = itemView.findViewById( R.id.Imagem );
        }


    }
}
