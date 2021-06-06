package com.example.pokedex_lj.model;

import java.util.List;

public class Pokemon {

    private Sprites sprites;
    private String name;
     private String id;
    private List<Stats> stats;
    private String username;

    public Pokemon() {
    }

    public Pokemon(Sprites sprites, String name, String id, List<Stats> stats) {
        this.sprites = sprites;
        this.name = name;
        this.id = id;
        this.stats = stats;
    }

    public Sprites getSprites() {
        return sprites;
    }

    public void setSprites(Sprites sprites) {
        this.sprites = sprites;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Stats> getStats() {
        return stats;
    }

    public void setStats(List<Stats> stats) {
        this.stats = stats;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
