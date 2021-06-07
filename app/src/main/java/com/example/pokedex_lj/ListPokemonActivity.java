package com.example.pokedex_lj;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pokedex_lj.model.Constants;
import com.example.pokedex_lj.model.Pokemon;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;

public class ListPokemonActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView pokemon_List;
    private LinearLayoutManager layoutManager;
    private TextInputEditText search_edit;
    private EditText atrapa_pokemon_text;
    private Button atrapar;
    private String nombre;
    private String username;
    private Button buscar;
    private LinearLayoutManager l;
    private PokemonAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buscar = findViewById(R.id.buscar);
        search_edit = findViewById(R.id.search_edit);
        atrapa_pokemon_text = findViewById(R.id.atrapa_pokemon_text);
        atrapar = findViewById(R.id.atrapar);
        pokemon_List = findViewById(R.id.pokemon_list);
        pokemon_List.setHasFixedSize(true);

        l = new LinearLayoutManager(this);
        pokemon_List.setLayoutManager(l);

        atrapar.setOnClickListener(this);
        username = getIntent().getExtras().getString("username");

        adapter = new PokemonAdapter();
        pokemon_List.setAdapter(adapter);



    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.atrapar:

                if(!atrapa_pokemon_text.getText().toString().isEmpty()) {

                    nombre = atrapa_pokemon_text.getText().toString();

                    HTTPSWebUtilDomi http = new HTTPSWebUtilDomi();

                    new Thread(
                            () -> {

                                String response = http.GETrequest("https://pokeapi.co/api/v2/pokemon/" + nombre);

                                Gson gson = new Gson();
                                Pokemon my_pokemon = gson.fromJson(response, Pokemon.class);

                                String json = gson.toJson(my_pokemon);
                                String resp = http.POSTrequest(Constants.base+"pokemons/"+username+".json", json);

                                Log.e("pokemon", my_pokemon.getStats().get(0).getBase_stat()+"");


                                runOnUiThread(
                                        () -> {
                                            //Toast.makeText(this,"Pokemon agregado", Toast.LENGTH_LONG);
                                            adapter.addPokemon(my_pokemon);



                                            //String respons = http.PUTrequest(Constants.base+"users/"+ user.getName() +".json",json);

                                            //Log.e("prueba", Constants.base+"pokemons/"+username+".json");
                                            //
                                        }
                                );




                            }
                    ).start();

                }

                break;

        }
    }


}