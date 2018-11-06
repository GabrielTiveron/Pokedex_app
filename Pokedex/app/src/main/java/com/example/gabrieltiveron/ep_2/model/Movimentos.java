package com.example.gabrieltiveron.ep_2.model;

import android.util.Log;

import com.example.gabrieltiveron.ep_2.pokeAPI.Servico;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Movimentos {
    private Movimento move;
    private int id;
    private Tipo.Tipagem type;

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
        private String name;
        private String url;


        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

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
        this.setMove( mov.getMove() );
        this.setType( mov.getType() );
    }

}


