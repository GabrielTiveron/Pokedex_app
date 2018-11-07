package com.example.gabrieltiveron.ep_2.model;

import com.example.gabrieltiveron.ep_2.controller.AdapterPokedex;
import com.example.gabrieltiveron.ep_2.pokeAPI.Servico;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Tipo {

    private Tipagem type;

    public Tipagem getType() {
        return type;
    }

    public void setType(Tipagem type) {
        this.type = type;
    }

    public class Tipagem {
        private String name;
        private String url;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }





}
