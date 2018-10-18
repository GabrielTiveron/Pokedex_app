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
import com.example.gabrieltiveron.ep_2.MainActivity;
import com.example.gabrieltiveron.ep_2.R;
import com.example.gabrieltiveron.ep_2.model.Pokemon;
import com.example.gabrieltiveron.ep_2.model.PokemonResposta;

import java.io.IOException;
import java.security.InvalidParameterException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class AdapterListaCadastro extends RecyclerView.Adapter<AdapterListaCadastro.ViewHolder> {

    private ArrayList<Pokemon> pokemon;
    private Context context;
    private Intent intent;

    public ArrayList<Pokemon> getPokemon() {
        return pokemon;
    }

    public AdapterListaCadastro(Context context) {
        pokemon = new ArrayList<>(  );
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterListaCadastro.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from( parent.getContext() ).inflate(R.layout.lista_cadastro_pokemon, parent, false);
        intent = new Intent( this.context, MainActivity.class );
        return new AdapterListaCadastro.ViewHolder( view );
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterListaCadastro.ViewHolder holder, int position) {
        Pokemon aux = pokemon.get( position );
        holder.textView.setText( aux.getName() );

        Glide.with(context)
                .load("http://pokeapi.co/media/sprites/pokemon/" + aux.getNumber() + ".png")
                .centerCrop()
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.imageView);
        intent.putExtra( "numero", aux.getNumber() );
        intent.putExtra( "nome", aux.getName() );
        intent.putExtra( "url", aux.getUrl() );
        intent.putExtra( "nomeTreinador", holder.textView.getText().toString() );
    }

    public void Adicionar(ArrayList<Pokemon> pokemonRespostas, String nome){

        int i = 0;

        while(!pokemonRespostas.isEmpty())
        {
            try {
                if (nome.compareTo( pokemonRespostas.get( i ).getName() ) == 0) {
                    pokemon.add( pokemonRespostas.get( i ) );
                    notifyDataSetChanged();
                    break;

                }
            }catch (Exception e){
                Log.e("ERROR", " Não encontrado ");
            }


            i++;
            if(i >= 950)break;
        }
    }

    @Override
    public int getItemCount() {
        return pokemon.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView imageView;
        private TextView textView;

        public ViewHolder(View itemView) {
            super( itemView );

            imageView = itemView.findViewById( R.id.imagem );
            textView = itemView.findViewById( R.id.texto );
        }
    }

    private class InvalidSpeedException extends Exception {

        public InvalidSpeedException(String message){
            super(message);
        }

    }


}
