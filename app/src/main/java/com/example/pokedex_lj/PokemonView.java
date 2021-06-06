package com.example.pokedex_lj;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pokedex_lj.model.Pokemon;

public class PokemonView extends RecyclerView.ViewHolder implements View.OnClickListener {

    private ConstraintLayout root;
    private ImageView image;
    private TextView name;
    private OnPokemonItemAction listener;
    private Button ver_pokemon;

    private Pokemon pokemon;

    public PokemonView(ConstraintLayout root) {
        super(root);
        this.root = root;
        image = root.findViewById(R.id.pokemon_img);
        name = root.findViewById(R.id.pokemon_name);
        ver_pokemon = root.findViewById(R.id.ver_pokemon);
        ver_pokemon.setOnClickListener(this);
    }

    public OnPokemonItemAction getListener() {
        return listener;
    }

    public Button getVer_pokemon() {
        return ver_pokemon;
    }

    public Pokemon getPokemon() {
        return pokemon;
    }

    public ConstraintLayout getRoot() {
        return root;
    }

    public ImageView getImage() {
        return image;
    }

    public TextView getName() {
        return name;
    }



    public void setListener(OnPokemonItemAction listener){
        this.listener = listener;
    }

    public void setPokemon(Pokemon pokemon) {
        this.pokemon = pokemon;
    }


    public interface OnPokemonItemAction{
        void onDeleteContact(Pokemon pokemon);
    }

    public interface pokeClicked{
        void pokeClicked(Pokemon pokemon, View v);
    }


    @Override
    public void onClick(View view) {
        if (ver_pokemon!=null){
            ver_pokemon.pokeClicked(this.pokemon,this.root);
        }
    }

    public void setVer_pokemon(pokeClicked ver_pokemon) {
        this.ver_pokemon = ver_pokemon;
    }
}
