package com.example.gabrieltiveron.ep_2.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
}
