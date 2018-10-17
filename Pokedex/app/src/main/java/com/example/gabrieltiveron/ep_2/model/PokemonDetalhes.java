package com.example.gabrieltiveron.ep_2.model;

import java.util.ArrayList;

public class PokemonDetalhes {
    private ArrayList<Tipo> types;

    public ArrayList<Tipo> getTypes() {
        return types;
    }

    public void setTypes(ArrayList<Tipo> types) {
        this.types = types;
    }

    public class Tipo
    {
        private Tipico type;

        public Tipico getType() {
            return type;
        }

        public void setType(Tipico type) {
            this.type = type;
        }
    }

    public class Tipico{
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
