package com.example.gabrieltiveron.ep_2.controller;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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
import com.example.gabrieltiveron.ep_2.helper.TreinadoresPreferences;
import com.example.gabrieltiveron.ep_2.model.Treinador;
import com.example.gabrieltiveron.ep_2.view.DetalhesTreinador;

import java.util.ArrayList;


public class AdapterListaTreinadores extends RecyclerView.Adapter<AdapterListaTreinadores.ViewHolder> {

    private ArrayList<Treinador> treinadores;
    private Context context;
    private TreinadoresPreferences sPreferences;

    public AdapterListaTreinadores(Context context) {
        treinadores = new ArrayList<>(  );
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from( context ).inflate(R.layout.adapter_lista_treinadores, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final Treinador treinador = treinadores.get( position );
        sPreferences = new TreinadoresPreferences( context );

        if (treinador.getSexo() != null && treinador.getNome() != null ) {
            holder.textView.setText( treinador.getNome() );

            if (treinador.getSexo().compareTo( "HOMEM" ) == 0) {
                holder.imageView.setImageResource( R.drawable.ash_perfil );
            } else {
                holder.imageView.setImageResource( R.drawable.may_perfil );
            }

            holder.textView.setOnLongClickListener( new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    popUpRemocao( context, holder.getAdapterPosition() );
                    return false;
                }
            } );

            holder.textView.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent( context, DetalhesTreinador.class );
                    intent.putExtra( "nomeTreinador", treinadores.get( position ).getNome() );
                    context.startActivity( intent );
                }
            } );
        }


    }

    public void adicionar(ArrayList<Treinador> treinador){
        treinadores.addAll(treinador);
        notifyDataSetChanged();
    }
    private void popUpRemocao(Context context, final int position) {

        AlertDialog.Builder dialog = new AlertDialog.Builder( context );
        dialog.setTitle( "Remover "  );
        dialog.setMessage( "Tem certeza que deseja excluir  esse treinador?" );
        dialog.setNegativeButton( "Não", null );
        dialog.setPositiveButton( "Sim",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        removerItem(treinadores.get( position ));
                    }
                } );
        dialog.create();
        dialog.show();
    }

    private void removerItem(Treinador treinador){
        sPreferences.removerTreinador( treinador );
        treinadores.remove( treinador );
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return treinadores.size();
    }

    private void setImagem(Context context, ViewHolder holder, int comp){
        Glide.with(context)
                .load(comp)
                .centerCrop()
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.imageView);
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView imageView;
        private TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super( itemView );

            imageView   = itemView.findViewById( R.id.imagemTreinador );
            textView    = itemView.findViewById( R.id.nomeTreinador );
        }

    }
}
