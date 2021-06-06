package com.example.pokedex_lj.model;

public class Usser {
    private String id;
    private String name;

    public Usser() {
    }

    public Usser(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
