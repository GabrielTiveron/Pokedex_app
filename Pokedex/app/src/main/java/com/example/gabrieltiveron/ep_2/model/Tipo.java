package com.example.gabrieltiveron.ep_2.model;

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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
