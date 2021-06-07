package com.example.pokedex_lj;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pokedex_lj.model.Pokemon;
import com.example.pokedex_lj.model.Sprites;

import java.util.ArrayList;

public class PokemonAdapter extends RecyclerView.Adapter<PokemonView> implements PokemonView.OnPokemonItemAction, PokemonView.pokeClicked{

    private ArrayList<Pokemon> pokemons;
    private Sprites sprites;

    public PokemonAdapter() {
        pokemons = new ArrayList<>();
    }

    public void addPokemon(Pokemon pokemon){
        pokemons.add(pokemon);
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PokemonView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View row = inflater.inflate(R.layout.pokemonrow, parent,false);
        ConstraintLayout rowroot = (ConstraintLayout) row;
        PokemonView pokemonView = new PokemonView(rowroot);
        pokemonView.setListener(this);
        return pokemonView;
    }

    @Override
    public void onBindViewHolder(@NonNull PokemonView holder, int position) {
        holder.setPokemon(pokemons.get(position));
        holder.getName().setText(pokemons.get(position).getName());
        holder.getImage().setImageDrawable(Drawable.createFromPath(pokemons.get(position).getSprites().getFront_default())); //setImageResource(pokemons.get(position).getStats().get(0).getBase_stat());

    }

    @Override
    public int getItemCount() {
        return pokemons.size();
    }


    @Override
    public void pokeClicked(Pokemon pokemon, View v) {

        Intent i = new Intent(v.getContext(), PokemonActivity.class);
        i.putExtra("pokemon", pokemons);
        v.getContext().startActivity(i);

    }
}
