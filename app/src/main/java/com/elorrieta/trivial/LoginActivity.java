package com.elorrieta.trivial;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.elorrieta.trivial.task.LoginAsyncTask;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ((Button) findViewById(R.id.btnLogIn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText textUsername = findViewById(R.id.textUsernameLogin);
                EditText textPassword = findViewById(R.id.textPasswordLogin);

                new LoginAsyncTask(getApplicationContext()).execute("LOGIN " + textUsername.getText().toString() + "," + textPassword.getText().toString());

            }
        });

    }
}