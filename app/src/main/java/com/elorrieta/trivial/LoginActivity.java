package com.elorrieta.trivial;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.elorrieta.trivial.client.Client;

import java.io.IOException;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ((Button) findViewById(R.id.btnLogIn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Client c = new Client();
                try {
                    c.getStream().writeObject("LOGIN Aingeru,Irazu1314");
                    System.out.println("Servidor: " + (String)c.getStream().readObject());
                } catch (ClassNotFoundException e) {
                    System.out.println("Error de casteo de I/O");
                    e.printStackTrace();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        });

    }
}