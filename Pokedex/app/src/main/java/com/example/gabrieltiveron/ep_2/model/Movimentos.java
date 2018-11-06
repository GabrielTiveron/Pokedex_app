package com.example.gabrieltiveron.ep_2.model;

import android.util.Log;

import com.example.gabrieltiveron.ep_2.pokeAPI.Servico;
import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Movimentos {
    private Movimento move;
    private int id;
    private Tipo.Tipagem type;
    private String name;
    private int accuracy;
    private int pp;
    private int power;

    public String getAccuracy() {
        return Integer.toString(accuracy);
    }

    public void setAccuracy(int accuracy) {
        this.accuracy = accuracy;
    }

    public String getPp() {
        return Integer.toString( pp );
    }

    public void setPp(int pp) {
        this.pp = pp;
    }

    public String getPower() {
        return Integer.toString( power );
    }

    public void setPower(int power) {
        this.power = power;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        String[] urlPartida = move.getUrl().split("/");
        return Integer.parseInt(urlPartida[urlPartida.length - 1]);
    }

    public Tipo.Tipagem getType() {
        return type;
    }

    public void setType(Tipo.Tipagem type) {
        this.type = type;
    }

    public Movimento getMove() {
        return move;
    }

    public void setMove(Movimento move) {
        this.move = move;
    }

    public class Movimento {
        private String url;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

    }

    public void obterMovimentos(Retrofit retrofit, int id) {
        Servico servico = retrofit.create( Servico.class );

        final Call<Movimentos> movimentosCall = servico.obterMovimento( id );

        movimentosCall.enqueue( new Callback<Movimentos>() {
            @Override
            public void onResponse(Call<Movimentos> call, Response<Movimentos> response) {
                if (response.isSuccessful()) {
                    Movimentos aux = response.body();
                    setAll( aux );


                } else {
                    Log.e( "OBTER DETALHES", "onResponse" );
                    response.raw().body().close();
                }
            }

            @Override
            public void onFailure(Call<Movimentos> call, Throwable t) {
                Log.e( "OBTER DETALHES", "onFailure" + t.getMessage() );
            }
        } );

    }
    private void setAll(Movimentos mov){
        this.setName( mov.getName() );
        this.setType( mov.getType() );
        this.setAccuracy( Integer.parseInt(mov.getAccuracy()) );
        this.setPower( Integer.parseInt(mov.getPower()) );
        this.setPp( Integer.parseInt( mov.getPp() ) );
    }

}


