package com.elorrieta.trivial;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.Objects;

public class LoadingActivity extends AppCompatActivity {

    class LoadingActivityAsyncTask extends AsyncTask<Integer, Integer, String> {

        @Override
        protected void onPreExecute() {
            getWindow().setFlags(
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
            );
        }

        @Override
        protected String doInBackground(Integer... params) {

            // TODO comprobación de usuarios en espera

            return "";
        }

        @Override
        protected void onPostExecute(String result) {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_loading );

        Objects.requireNonNull(getSupportActionBar()).hide();

        ConstraintLayout constraintLayout = findViewById(R.id.constraintLayout);
        constraintLayout.setBackgroundColor( Color.parseColor("#ffffff"));

        constraintLayout.setOnTouchListener((v, m) -> {
            Intent intent = new Intent( LoadingActivity.this, MainActivity.class );
            startActivity(intent);
            return true;
        });

        ImageView imageView = findViewById(R.id.imageLoading);
        Glide.with(this).load(R.raw.loading).into(imageView);

        new LoadingActivityAsyncTask().execute();
    }
}