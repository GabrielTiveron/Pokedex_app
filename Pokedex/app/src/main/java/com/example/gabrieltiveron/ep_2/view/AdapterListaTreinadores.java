package com.example.gabrieltiveron.ep_2.view;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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
import com.example.gabrieltiveron.ep_2.model.Treinador;

import java.util.ArrayList;


public class AdapterListaTreinadores extends RecyclerView.Adapter<AdapterListaTreinadores.ViewHolder> {

    private ArrayList<Treinador> treinadores;
    private Context context;

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
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        Treinador treinador = treinadores.get( position );
        Log.e("HAKAKA", " nome " + treinador.getNome());
        Log.e("HAKAKA", " size " + treinadores.size());

        holder.textView.setText( treinador.getNome() );

        holder.textView.setOnLongClickListener( new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                popUpRemocao( context, holder.getAdapterPosition() );
                return false;
            }
        });


    }

    public void adicionar(ArrayList<Treinador> treinador){
        treinadores.addAll(treinador);
        notifyDataSetChanged();
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

    private void removerItem(int position){
        treinadores.remove( position );
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
