package com.example.gabrieltiveron.ep_2.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gabrieltiveron.ep_2.R;
import com.example.gabrieltiveron.ep_2.helper.TreinadoresPreferences;
import com.example.gabrieltiveron.ep_2.model.Treinador;

public class DetalhesTreinador extends AppCompatActivity {

    private Intent intent;
    private TreinadoresPreferences sPreferences;
    private Treinador treinador;
    private ImageView imagem;
    private TextView nome;
    private RecyclerView recyclerView;
    private AdapterListaCadastro listaPokemon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_detalhes_treinador );
        sPreferences = new TreinadoresPreferences( getApplicationContext() );
        listaPokemon = new AdapterListaCadastro( this );
        intent = new Intent(  );

        String nomeTreinador = intent.getStringExtra( "nome" );

        imagem = findViewById( R.id.imagemTreinador );
        nome = findViewById( R.id.nomeTreinador );
        recyclerView = findViewById( R.id.listaPokemon );

        try {
            treinador = sPreferences.getTreinador( nomeTreinador );
        } catch(Exception e) {
            e.printStackTrace();
        }
        if (treinador != null) {


//            if (treinador.getSexo().compareTo( "MULHER" ) == 0) {
//                imagem.setImageResource( R.drawable.may );
//            } else {
//                imagem.setImageResource( R.drawable.ash );
//            }

            nome.setText( treinador.getNome() );

            recyclerView.setAdapter( listaPokemon );
            recyclerView.setLayoutManager( new LinearLayoutManager( this ) );

            listaPokemon.adicionarLista( treinador.getPokemons() );
        } else {
            Toast.makeText( this, "Erro ao encontrar treinador", Toast.LENGTH_SHORT ).show();
        }


        confirgurarBotaoRetorno();

    }

    private void confirgurarBotaoRetorno(){
        Button btnRetorno = (Button) findViewById( R.id.btnRetorno );

        btnRetorno.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        } );
    }

}
