package com.example.gabrieltiveron.ep_2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.gabrieltiveron.ep_2.view.Pokedex;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        configurarBotaoCadastro();






    }

    private void configurarBotaoCadastro(){
        Button buttonCadastro = (Button) findViewById( R.id.pokedex );

        buttonCadastro.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Pokedex.class ));
            }
        } );
    }

}
