package com.example.pokedex_lj;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pokedex_lj.model.Pokemon;

import java.util.ArrayList;

public class PokemonAdapter extends RecyclerView.Adapter<PokemonView> implements PokemonView.OnPokemonItemAction, PokemonView.pokeClicked{

    private ArrayList<Pokemon> pokemons;

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
        View row = inflater.inflate(R.layout.pokemonrow, null);
        ConstraintLayout rowroot = (ConstraintLayout) row;
        PokemonView pokemonView = new PokemonView(rowroot);
        pokemonView.setListener(this);
        return pokemonView;
    }

    @Override
    public void onBindViewHolder(@NonNull PokemonView holder, int position) {
        holder.setPokemon(pokemons.get(position));
        holder.getName().setText(pokemons.get(position).getName());
        holder.getImage().setImageResource(pokemons.get(position).getStats().get(0).getBase_stat());
        holder.setVer_pokemon(this);


    }

    @Override
    public int getItemCount() {
        return pokemons.size();
    }

    @Override
    public void onDeleteContact(Pokemon pokemon) {
        
    }

    @Override
    public void pokeClicked(Pokemon pokemon, View v) {

        Intent i = new Intent(v.getContext(), PokemonActivity.class);
        i.putExtra("pokemon", pokemon);
        v.getContext().startActivity(i);

    }
}
