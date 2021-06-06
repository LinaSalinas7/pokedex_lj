package com.example.pokedex_lj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.pokedex_lj.model.Constants;
import com.example.pokedex_lj.model.Usser;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;

import java.util.UUID;

public class UserActivity extends AppCompatActivity implements View.OnClickListener {

    private TextInputEditText username_texfield_edit;
    private TextInputEditText password_texfield_edit;
    private Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        username_texfield_edit = findViewById(R.id.username_texfield_edit);
        password_texfield_edit = findViewById(R.id.password_texfield_edit);
        login = findViewById(R.id.login);

        login.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.login:
                Usser user = new Usser(UUID.randomUUID().toString(), username_texfield_edit.getText().toString());
                Gson gson = new Gson();
                String json = gson.toJson(user);

                HTTPSWebUtilDomi https = new HTTPSWebUtilDomi();

                new Thread(
                        ()->{

                            String respons = https.PUTrequest(Constants.base+"users/"+ user.getName() +".json",json);
                            Log.e("prueba", Constants.base+"users/"+ user.getName() +".json");

                            runOnUiThread(
                                    ()->{
                                        Intent i = new Intent(this, ListPokemonActivity.class);
                                        i.putExtra("username", user.getName());
                                        startActivity(i);
                                    }
                            );

                        }
                ).start();

                break;
        }
    }

}