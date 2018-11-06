package com.example.gabrieltiveron.ep_2.controller;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gabrieltiveron.ep_2.R;
import com.example.gabrieltiveron.ep_2.model.Movimentos;

import java.util.ArrayList;

public class AdapterMovimentos extends RecyclerView.Adapter<AdapterMovimentos.ViewHolder> {

    private ArrayList<Movimentos> movimentos;
    private Context context;

    public AdapterMovimentos(Context context) {
        movimentos = new ArrayList<>(  );
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from( parent.getContext() ).inflate(R.layout.adapter_movimentos, parent, false);
        return new ViewHolder( view );
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Movimentos movimento = movimentos.get( position );

        try{
        holder.moveNome.setText( movimentos.get( position ).getName() );
        holder.moveType.setText( movimento.getType().getName() );
        definirBackground(movimento.getType().getName(), holder);
        holder.moveAccuracy.setText( movimento.getAccuracy() );
        holder.movePower.setText( movimento.getPower() );
        holder.movePp.setText( movimento.getPp() );

        } catch(Exception e){
            e.printStackTrace();
        }


    }

    @Override
    public int getItemCount() {
        return movimentos.size();
    }

    public void adicionarMov(ArrayList<Movimentos> moves) {
        movimentos.addAll(moves);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView moveNome;
        private TextView moveType;
        private TextView moveAccuracy;
        private TextView movePower;
        private TextView movePp;

        public ViewHolder(View itemView) {
            super( itemView );

            moveNome     = itemView.findViewById( R.id.moveNome );
            moveType     = itemView.findViewById( R.id.moveTipo );
            moveAccuracy = itemView.findViewById( R.id.accuracyView );
            movePower    = itemView.findViewById( R.id.powerView );
            movePp       = itemView.findViewById( R.id.movePP );

        }
    }

    private void definirBackground(String tipo, ViewHolder holder) {
        switch (tipo) {
            case "normal":
                holder.moveType.setBackground(context.getResources().getDrawable(R.drawable.tipo_normal));
                break;
            case "fighting":
                holder.moveType.setBackground(context.getResources().getDrawable(R.drawable.tipo_fighting));
                break;
            case "flying":
                holder.moveType.setBackground(context.getResources().getDrawable(R.drawable.tipo_flying));
                break;
            case "poison":
                holder.moveType.setBackground(context.getResources().getDrawable(R.drawable.tipo_poison));
                break;
            case "ground":
                holder.moveType.setBackground(context.getResources().getDrawable(R.drawable.tipo_ground));
                break;
            case "rock":
                holder.moveType.setBackground(context.getResources().getDrawable(R.drawable.tipo_rock));
                break;
            case "bug":
                holder.moveType.setBackground(context.getResources().getDrawable(R.drawable.tipo_bug));
                break;
            case "ghost":
                holder.moveType.setBackground(context.getResources().getDrawable(R.drawable.tipo_ghost));
                break;
            case "steel":
                holder.moveType.setBackground(context.getResources().getDrawable(R.drawable.tipo_steel));
                break;
            case "fire":
                holder.moveType.setBackground(context.getResources().getDrawable(R.drawable.tipo_fire));
                break;
            case "water":
                holder.moveType.setBackground(context.getResources().getDrawable(R.drawable.tipo_water));
                break;
            case "grass":
                holder.moveType.setBackground(context.getResources().getDrawable(R.drawable.tipo_grass));
                break;
            case "electric":
                holder.moveType.setBackground(context.getResources().getDrawable(R.drawable.tipo_eletric));
                break;
            case "psychic":
                holder.moveType.setBackground(context.getResources().getDrawable(R.drawable.tipo_psychic));
                break;
            case "ice":
                holder.moveType.setBackground(context.getResources().getDrawable(R.drawable.tipo_ice));
                break;
            case "dragon":
                holder.moveType.setBackground(context.getResources().getDrawable(R.drawable.tipo_dragon));
                break;
            case "dark":
                holder.moveType.setBackground(context.getResources().getDrawable(R.drawable.tipo_dark));
                break;
            case "fairy":
                holder.moveType.setBackground(context.getResources().getDrawable(R.drawable.tipo_fairy));
                break;
            case "shadow":
                holder.moveType.setBackground(context.getResources().getDrawable(R.drawable.tipo_shadow));
                break;
            default:
                holder.moveType.setBackground(context.getResources().getDrawable(R.drawable.tipo_normal));
                break;

        }
    }
}
