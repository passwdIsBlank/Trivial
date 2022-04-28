package com.elorrieta.trivial;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private static final int LOGIN_ACTIVITY = 1;
    private Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);

        ((Button) findViewById(R.id.btnSinglePlayer)).setOnClickListener( (v) -> {

            Intent intent = new Intent(getApplicationContext(), CategoryActivity.class);
            startActivity(intent);

        });

        ((Button) findViewById(R.id.btnMultiPlayer)).setOnClickListener( (v) -> {

            Intent intent = new Intent(getApplicationContext(), LobbyActivity.class);
            startActivity(intent);

        });

        ((Button) findViewById(R.id.btnRanking)).setOnClickListener( (v) -> {

            Intent intent = new Intent(getApplicationContext(), RankingActivity.class);
            startActivity(intent);

        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.loginmenu, menu);

        this.menu = menu;
        toggleUserActions();

        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        String loginTitle = getString(R.string.action_login);

        if (item.getItemId() == R.id.action_login){
            if (!loginTitle.contentEquals(item.getTitle())) {
                SharedPreferences sharedPref = getSharedPreferences("session", Context.MODE_PRIVATE);
                SharedPreferences.Editor sharedPrefEditor = sharedPref.edit();

                sharedPrefEditor.remove("username")
                        .apply();

                toggleUserActions();
            } else {
                Intent intent = new Intent(getApplicationContext(), LoginRegisterActivity.class);
                startActivityForResult(intent, LOGIN_ACTIVITY);
            }
        }

        return  true;

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == LOGIN_ACTIVITY && resultCode == RESULT_OK) {
            toggleUserActions();
        }
    }

    private void toggleUserActions () {
        SharedPreferences sharedpreferences = getSharedPreferences("session", Context.MODE_PRIVATE);
        String username = sharedpreferences.getString("username", "");
        String loginAction = getString(R.string.action_login);
        boolean isLogged = !username.equals("");

        ((Button) findViewById(R.id.btnMultiPlayer)).setEnabled(isLogged);
        ((Button) findViewById(R.id.btnRanking)).setEnabled(isLogged);

        if (getSupportActionBar() != null) {
            if (isLogged)
                loginAction = getString(R.string.action_logout);
            menu.findItem(R.id.username).setTitle(username);
            menu.findItem(R.id.action_login).setTitle(loginAction);
        }
    }

}