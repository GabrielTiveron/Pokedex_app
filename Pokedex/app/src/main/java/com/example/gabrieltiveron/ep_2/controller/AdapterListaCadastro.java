package com.example.gabrieltiveron.ep_2.controller;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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
import com.example.gabrieltiveron.ep_2.view.MainActivity;

import java.util.ArrayList;

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
        View view = LayoutInflater.from( parent.getContext() ).inflate(R.layout.adapter_lista_pokemon, parent, false);
        intent = new Intent( this.context, MainActivity.class );
        return new AdapterListaCadastro.ViewHolder( view );
    }

    @Override
    public void onBindViewHolder(@NonNull final AdapterListaCadastro.ViewHolder holder, final int position) {
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

        holder.textView.setOnLongClickListener( new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                popUpRemocao( context, holder.getAdapterPosition() );
                return true;
            }
        } );

        holder.imageView.setOnLongClickListener( new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                popUpZoom(context, holder);

                return true;
            }
        } );
    }

    public void adicionar(ArrayList<Pokemon> pokemonRespostas, String nome){

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

    public void adicionarLista(ArrayList<Pokemon> pokemons) {
        pokemon.addAll( pokemons );
        notifyDataSetChanged();
    }

    private void removerItem(int position){
        pokemon.remove( position );
        notifyDataSetChanged();
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

    private void popUpRemocao(Context context, final int position) {

        AlertDialog.Builder dialog = new AlertDialog.Builder( context );
        dialog.setTitle( "Remover "  );
        dialog.setMessage( "Tem certeza que deseja excluir  esse pokemon?" );
        dialog.setNegativeButton( "Não", null );
        dialog.setPositiveButton( "Sim",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        removerItem(position);
                    }
                } );
        dialog.create();
        dialog.show();
    }

    private void popUpZoom(Context context, final ViewHolder holder){


        AlertDialog.Builder dialog = new AlertDialog.Builder( context );

        ((ViewGroup)holder.imageView.getParent()).removeView( holder.imageView );

        dialog.setView(holder.imageView);
        dialog.setNeutralButton( "Sair", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                atualizarImagem( holder, holder.getAdapterPosition() );
            }
        } );

        dialog.create();
        dialog.show();
    }

    private void atualizarImagem(ViewHolder holder, int position){
        Glide.with(context)
                .load("http://pokeapi.co/media/sprites/pokemon/" + pokemon.get( position ).getNumber() + ".png")
                .centerCrop()
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.imageView);
        notifyDataSetChanged();
    }

}
