package com.example.gabrieltiveron.ep_2.model;

import java.util.ArrayList;

public class Pokemon {

    private int number;
    private String name;
    private String url;
    private ArrayList<Tipo> types;

    public ArrayList<Tipo> getTypes() {
        return types;
    }

    public void setTypes(ArrayList<Tipo> types) {
        this.types = types;
    }

    public int getNumber() {
        String[] urlPartes = url.split( "/" );
        return Integer.parseInt(urlPartes[urlPartes.length-1]);
    }

    public void setNumber(int number) {
        this.number = number;
    }

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
