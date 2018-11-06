package com.example.gabrieltiveron.ep_2.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.gabrieltiveron.ep_2.R;
import com.example.gabrieltiveron.ep_2.model.Movimentos;
import com.google.gson.Gson;

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

        Gson gson = new Gson();

        String a = gson.toJson( movimentos.get( position ) );

        Log.e("NOME"," = " + a);

        try{
        holder.moveNome.setText( movimentos.get( position ).getMove().getName() );
        } catch(Exception e){
            e.printStackTrace();
        }
//        holder.moveType.setText( movimento.getType().getName() );

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

        public ViewHolder(View itemView) {
            super( itemView );

            moveNome = itemView.findViewById( R.id.moveNome );
            moveType = itemView.findViewById( R.id.moveType );

        }
    }
}
