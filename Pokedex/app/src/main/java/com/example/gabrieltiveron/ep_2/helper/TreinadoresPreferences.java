package com.example.gabrieltiveron.ep_2.helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.gabrieltiveron.ep_2.model.Treinador;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;

public class TreinadoresPreferences {

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private static final String ARQUIVO     = "Lista_Treinadores";
    private static final String KEY_LISTA   = "listaTreinadores";
    private int MODE = 0;
    private Context context;
    private ArrayList<Treinador> treinadores;

    public TreinadoresPreferences(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(ARQUIVO, MODE);
    }

    public boolean salvarTreinador(Treinador treinador){
        try {
            treinadores = new ArrayList<>();

            if (this.getTreinadores() != null) {
                treinadores = new ArrayList<>( Arrays.asList( this.getTreinadores() ) );
            }

            editor = sharedPreferences.edit();
            Gson gson = new Gson();
            treinadores.add( treinador );
            String json = gson.toJson( treinadores );
            editor.putString( KEY_LISTA, json );
            editor.apply();

            return true;
        }
        catch (Exception e){
            e.printStackTrace();

            return false;
        }
    }
    public void removerTreinador(Treinador treinador){
        try {
            treinadores = new ArrayList<>();

            editor = sharedPreferences.edit();
            Gson gson = new Gson();
                treinadores.remove( treinador );
                String json = gson.toJson( treinadores );
                Log.e( "JSON", " stringJson " + json );
                editor.putString( KEY_LISTA, json );
                editor.commit();
                editor.clear();

        }
        catch (Exception e){
            e.printStackTrace();

        }
    }

    public Treinador[] getTreinadores(){
        Gson gson = new Gson();
        String json = sharedPreferences.getString(KEY_LISTA, "");


        return gson.fromJson( json, Treinador[].class );
    }

    public Treinador getTreinador(String nome) {
        Treinador treinador = new Treinador(  );
        ArrayList<Treinador> treinadorArrayList;

        try {
            treinadorArrayList = new ArrayList<>( Arrays.asList( this.getTreinadores() ) );

            for (Treinador t : treinadorArrayList){
                if(t.getNome().compareTo( nome ) == 0){
                    return t;
                }
            }
        }catch(Exception e) {
            e.printStackTrace();
        }

        return treinador;
    }


}
