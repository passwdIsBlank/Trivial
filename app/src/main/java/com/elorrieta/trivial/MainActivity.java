package com.elorrieta.trivial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((Button) findViewById(R.id.btnSinglePlayer)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(),CategoryActivity.class);
                startActivity(intent);

            }
        });

        ((Button) findViewById(R.id.btnMultyPlayer)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(),LoadingActivity.class);
                startActivity(intent);

            }
        });

        ((Button) findViewById(R.id.btnRanking)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(),RankingActivity.class);
                startActivity(intent);

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.loginmenu, menu);

        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
        startActivity(intent);

        return  true;

    }

}