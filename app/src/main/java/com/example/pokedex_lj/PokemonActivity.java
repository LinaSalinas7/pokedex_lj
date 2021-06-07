package com.example.pokedex_lj;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pokedex_lj.model.Constants;
import com.example.pokedex_lj.model.Pokemon;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.security.Key;
import java.util.HashMap;

public class PokemonActivity extends AppCompatActivity {

    private Button soltarButton;
    private ImageView detail_img;
    private TextView detail_name;
    private TextView detail_attack;
    private TextView detail_speed;
    private TextView detail_life;
    private TextView detail_defensa;
    private MaterialToolbar toolbar;

    private String username;

    private Pokemon pokemon;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon);

        soltarButton = findViewById(R.id.soltar_button);
        detail_img = findViewById(R.id.detail_img);
        detail_name = findViewById(R.id.detail_name);
        detail_attack = findViewById(R.id.detail_attack);
        detail_speed = findViewById(R.id.detail_speed);
        detail_life = findViewById(R.id.detail_life);
        toolbar = findViewById(R.id.toolbar);
        detail_defensa = findViewById(R.id.detail_defensa);

        username = getIntent().getExtras().getString("username");

        getPokemons();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void getPokemons() {
        HTTPSWebUtilDomi https = new HTTPSWebUtilDomi();
        new Thread(
                ()->{
                    String response = https.GETrequest(Constants.base+"pokemons/"+ ".json");
                    Type tipe = new TypeToken<HashMap<String, Pokemon>>(){}.getType();
                    Gson gson = new Gson();
                    HashMap<String, Pokemon> pokemons = gson.fromJson(response, tipe);
                    pokemons.forEach(
                            (Key, value)->{
                                runOnUiThread(
                                        ()->{
                                            detail_name.append(value.getName());
                                            detail_life.append(value.getStats().get(0).getBase_stat()+"");
                                            detail_attack.append(value.getStats().get(1).getBase_stat()+"");
                                            detail_speed.append(value.getStats().get(5).getBase_stat()+"");
                                            detail_defensa.append(value.getStats().get(2).getBase_stat()+"");
                                            String url = pokemon.getSprites().getFront_default();
                                            //Glide.with(this).load(url).fitCenter().into(detail_img);
                                            //detail_img.setImageResource(pokemon.getSprites().);

                                        }
                                );
                            }
                    );

                }
        );
    }
}